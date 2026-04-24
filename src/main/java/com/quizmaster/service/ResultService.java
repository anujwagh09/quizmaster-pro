package com.quizmaster.service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmaster.dto.SubmitRequestDTO;
import com.quizmaster.dto.SubmitResponseDTO;
import com.quizmaster.entity.Question;
import com.quizmaster.entity.Quiz;
import com.quizmaster.entity.Result;
import com.quizmaster.entity.User;
import com.quizmaster.exception.ResourceNotFoundException;
import com.quizmaster.repository.QuestionRepository;
import com.quizmaster.repository.QuizRepository;
import com.quizmaster.repository.ResultRepository;
import com.quizmaster.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepo;

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private QuizRepository quizRepo;

    @Autowired
    private UserRepository userRepo;

    public SubmitResponseDTO submitQuiz(SubmitRequestDTO sreq) {

        if (sreq == null) {
            throw new RuntimeException("Request body is missing");
        }

        if (sreq.getQuizId() == null) {
            throw new RuntimeException("Quiz ID is required");
        }

        if (sreq.getAnswers() == null || sreq.getAnswers().isEmpty()) {
            throw new RuntimeException("Answers cannot be null or empty");
        }

        Quiz q = quizRepo.findById(sreq.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        String email = auth.getName();

        User u = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Question> questions = questionRepo.findByQuizId(sreq.getQuizId());

        int score = 0;

        Map<Long, String> answerMap = new HashMap<>();

        for (SubmitRequestDTO.AnswerDTO a : sreq.getAnswers()) {
            if (a == null || a.getQuestionId() == null || a.getSelectedOption() == null) {
                throw new RuntimeException("Invalid answer data");
            }
            answerMap.put(a.getQuestionId(), a.getSelectedOption());
        }

        for (Question question : questions) {
            String userAns = answerMap.get(question.getId());
            if (userAns != null &&
                question.getCorrectAnswer() != null &&
                userAns.equalsIgnoreCase(question.getCorrectAnswer())) {
                score++;
            }
        }

        Result result = new Result();
        result.setQuiz(q);
        result.setUser(u);
        result.setScore(score);
        result.setTotalQuestions(questions.size());
        result.setTimeTaken(sreq.getTimeTaken());
        resultRepo.save(result);

        SubmitResponseDTO response = new SubmitResponseDTO();
        response.setScore(score);
        response.setTotalQuestions(questions.size());
        response.setTimeTaken(sreq.getTimeTaken());
        response.setMessage("You scored " + score + " out of " + questions.size());

        return response;
    }

    public List<Result> getLeaderboard(Long quizId) {
        return resultRepo.findByQuizIdOrderByScoreDescTimeTakenAsc(quizId);
    }

    public List<Result> getMyResults(Long userId) {
        return resultRepo.findByUserId(userId);
    }
}

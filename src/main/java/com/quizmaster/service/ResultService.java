package com.quizmaster.service;

import java.util.List;
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
		Quiz q = quizRepo.findById(sreq.getQuizId())
				.orElseThrow(() -> new ResourceNotFoundException("Quiz not found "));
		User u = userRepo.findById(sreq.getUserId()).orElseThrow(() ->new ResourceNotFoundException("User not found " ));

		List<Question> questions = questionRepo.findByQuizId(sreq.getQuizId());

		int score = 0;
		Map<Long, String> userAnswer = sreq.getAnswers();

		for (Question question : questions) {
			String userAns = userAnswer.get(question.getId());
			System.out.println("Q ID: " + question.getId() + " | Correct: " + question.getCorrectAnswer()
					+ " | User Answer: " + userAnswer);
			if (userAns != null && userAns.equalsIgnoreCase(question.getCorrectAnswer()))
				score++;

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

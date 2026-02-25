package com.quizmaster.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmaster.dto.QuestionResponseDTO;
import com.quizmaster.dto.QuizResponseDTO;
import com.quizmaster.entity.Question;
import com.quizmaster.entity.Quiz;
import com.quizmaster.exception.ResourceNotFoundException;
import com.quizmaster.repository.QuestionRepository;
import com.quizmaster.repository.QuizRepository;

@Service
public class UserQuizService {
	@Autowired
	private QuizRepository quizRepo;
	@Autowired
	private QuestionRepository questionRepo;

	public List<QuizResponseDTO> getAllQuizzes() {

		List<Quiz> quizzes = quizRepo.findAll();
		List<QuizResponseDTO> dtoList = new ArrayList<>();

		for (Quiz quiz : quizzes) {
			QuizResponseDTO dto = new QuizResponseDTO();
			dto.setId(quiz.getId());
			dto.setTitle(quiz.getTitle());
			dto.setTopic(quiz.getTopic());
			dto.setDuration(quiz.getDuration());

			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<QuestionResponseDTO> startQuiz(Long quizId) {

		Quiz quiz = quizRepo.findById(quizId).orElseThrow(() ->new ResourceNotFoundException("Quiz not found "));

		List<Question> questions = questionRepo.findByQuizId(quizId);
		List<QuestionResponseDTO> dtoList = new ArrayList<>();

		for (Question q : questions) {
			QuestionResponseDTO dto = new QuestionResponseDTO();
			dto.setId(q.getId());
			dto.setQuestionText(q.getQuestionText());
			dto.setOptionA(q.getOptionA());
			dto.setOptionB(q.getOptionB());
			dto.setOptionC(q.getOptionC());
			dto.setOptionD(q.getOptionD());

			dtoList.add(dto);
		}

		return dtoList;
	}

	private QuizResponseDTO mapToQuizDTO(Quiz quiz) {
		QuizResponseDTO dto = new QuizResponseDTO();
		dto.setId(quiz.getId());
		dto.setTitle(quiz.getTitle());
		dto.setTopic(quiz.getTopic());
		dto.setDuration(quiz.getDuration());
		return dto;
	}

	private QuestionResponseDTO mapToQuestionDTO(Question q) {
		QuestionResponseDTO dto = new QuestionResponseDTO();
		dto.setId(q.getId());
		dto.setQuestionText(q.getQuestionText());
		dto.setOptionA(q.getOptionA());
		dto.setOptionB(q.getOptionB());
		dto.setOptionC(q.getOptionC());
		dto.setOptionD(q.getOptionD());
		return dto;
	}
}

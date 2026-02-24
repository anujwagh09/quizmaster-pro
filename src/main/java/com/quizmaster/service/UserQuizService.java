package com.quizmaster.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmaster.dto.QuizResponseDTO;
import com.quizmaster.entity.Quiz;
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
}

package com.quizmaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmaster.entity.Question;
import com.quizmaster.entity.Quiz;
import com.quizmaster.exception.ResourceNotFoundException;
import com.quizmaster.repository.QuestionRepository;
import com.quizmaster.repository.QuizRepository;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository qr;
	
	@Autowired 
	private QuizRepository quizRepo;
	
	public Question addQuestion(Long id,Question q) {
		Quiz qz=quizRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Quiz not found with id: " + id));
		Question que=new Question();
		que.setQuestionText(q.getQuestionText());
		que.setOptionA(q.getOptionA());
		que.setOptionB(q.getOptionB());
		que.setOptionC(q.getOptionC());
		que.setOptionD(q.getOptionD());
		que.setCorrectAnswer(q.getCorrectAnswer());
		que.setQuiz(qz);
		return qr.save(que);
	}
	
	public void deleteQuestion(Long id) {
		 qr.deleteById(id);
	}
	public List<Question> getQuestionsByQuizId(Long quizId) {
        return qr.findByQuizId(quizId);
    }
	
	 public Question updateQuestion(Long id, Question q) {
	        Question question = qr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));

	        question.setQuestionText(q.getQuestionText());
	        question.setOptionA(q.getOptionA());
	        question.setOptionB(q.getOptionB());
	        question.setOptionC(q.getOptionC());
	        question.setOptionD(q.getOptionD());
	        question.setCorrectAnswer(q.getCorrectAnswer());

	        return qr.save(question);
	    }
	

}

package com.quizmaster.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizmaster.entity.Quiz;
import com.quizmaster.repository.QuizRepository;


@Service
public class QuizService {

	@Autowired
	private QuizRepository qr;

	public Quiz createQuiz(Quiz q) {
		Quiz q1 = new Quiz();
		q1.setTitle(q.getTitle());
		q1.setTopic(q.getTopic());
		q1.setDuration(q.getDuration());
		q1.setCreatedAt(LocalDateTime.now());
		return qr.save(q1);
	}

	public List<Quiz> getAllQuiz() {
		return qr.findAll();
	}

	public Quiz getQuizById(Long id) {
		return qr.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
	}

	public Quiz updateQuiz(Long id, Quiz q) {
		Quiz quiz = getQuizById(id);
		quiz.setTitle(q.getTitle());
		quiz.setTopic(q.getTopic());
		quiz.setDuration(q.getDuration());
		return qr.save(quiz);
	}

	public String deleteQuiz(Long id) {
		qr.deleteById(id);
		return "Quiz deleted successfully";
	}

}

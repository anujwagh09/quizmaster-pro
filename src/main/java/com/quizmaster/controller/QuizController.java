package com.quizmaster.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizmaster.entity.Quiz;
import com.quizmaster.repository.QuizRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/quizcon")
public class QuizController {
	@Autowired
	private QuizRepository qr;
	
	@PostMapping
	public Quiz createQuiz(@Valid @RequestBody Quiz q) {
		q.setCreatedAt(LocalDateTime.now());
		return qr.save(q);
	}
	@GetMapping
	public List<Quiz> getAllQuiz(){
		return qr.findAll();
	}
	
	@GetMapping("/{id}")
    public Optional<Quiz> getQuizById(@PathVariable Long id) {
        return qr.findById(id);               
    }
	 @DeleteMapping("/{id}")
	    public String deleteQuiz(@PathVariable Long id) {
	       qr.deleteById(id);
	        return "Quiz deleted successfully";
	    }
}

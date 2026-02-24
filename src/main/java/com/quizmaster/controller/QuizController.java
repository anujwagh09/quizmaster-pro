package com.quizmaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizmaster.entity.Quiz;
import com.quizmaster.service.QuizService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/createquiz")
public class QuizController {
	@Autowired
	private QuizService qs;
	
	@PostMapping
	public Quiz createQuiz(@Valid @RequestBody Quiz q) {
		return qs.createQuiz(q);
	}
	@GetMapping
	public List<Quiz> getAllQuiz(){
		return qs.getAllQuiz();
	}
	
	@GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return qs.getQuizById(id);               
    }
	 @DeleteMapping("/{id}")
	    public String deleteQuiz(@PathVariable Long id) {
	       qs.deleteQuiz(id);
	        return "Quiz deleted successfully";
	    }
}

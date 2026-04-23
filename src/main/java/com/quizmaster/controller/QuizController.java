package com.quizmaster.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.quizmaster.entity.Quiz;
import com.quizmaster.service.QuizService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService qs;

    @PostMapping("/create")
    public Quiz createQuiz(@Valid @RequestBody Quiz q) {
        return qs.createQuiz(q);
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

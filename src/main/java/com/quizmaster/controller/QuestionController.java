package com.quizmaster.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.quizmaster.entity.Question;
import com.quizmaster.service.QuestionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService qs;

    @PostMapping("/quiz/{quizId}")
    public Question addQuestion(@PathVariable Long quizId,
                                @Valid @RequestBody Question question) {
        return qs.addQuestion(quizId, question);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsByQuiz(@PathVariable Long quizId) {
        return qs.getQuestionsByQuizId(quizId);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id,
                                   @RequestBody Question question) {
        return qs.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        qs.deleteQuestion(id);
        return "Question deleted successfully";
    }
}

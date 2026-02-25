package com.quizmaster.controller;

	import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quizmaster.dto.QuestionResponseDTO;
import com.quizmaster.dto.QuizResponseDTO;
import com.quizmaster.service.UserQuizService;

@RestController
@RequestMapping("/api/quiz")
public class UserQuizController {

	@Autowired
	private UserQuizService userQuizService;

	@GetMapping
	public ResponseEntity<List<QuizResponseDTO>> getAllQuizzes() {
		return ResponseEntity.ok(userQuizService.getAllQuizzes());
	}

	@GetMapping("/{quizId}/start")
	public ResponseEntity<List<QuestionResponseDTO>> startQuiz(@PathVariable Long quizId) {
		return ResponseEntity.ok(userQuizService.startQuiz(quizId));
	}
}
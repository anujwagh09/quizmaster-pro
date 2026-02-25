package com.quizmaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quizmaster.dto.SubmitRequestDTO;
import com.quizmaster.dto.SubmitResponseDTO;
import com.quizmaster.entity.Result;
import com.quizmaster.service.ResultService;

@RestController
@RequestMapping("/api/quiz")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/submit")
    public ResponseEntity<SubmitResponseDTO> submitQuiz(@RequestBody SubmitRequestDTO dto) {
        return ResponseEntity.ok(resultService.submitQuiz(dto));
    }
    
    @GetMapping("/leaderboard/{quizId}")
    public ResponseEntity<List<Result>> getLeaderboard(@PathVariable Long quizId) {
        return ResponseEntity.ok(resultService.getLeaderboard(quizId));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Result>> getMyResults(@PathVariable Long userId) {
        return ResponseEntity.ok(resultService.getMyResults(userId));
    }
}

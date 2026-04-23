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
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/api/attempt/submit")
    public ResponseEntity<SubmitResponseDTO> submitQuiz(@RequestBody SubmitRequestDTO dto) {
        return ResponseEntity.ok(resultService.submitQuiz(dto));
    }

    @GetMapping("/api/leaderboard/{quizId}")
    public ResponseEntity<List<Result>> getLeaderboard(@PathVariable Long quizId) {
        return ResponseEntity.ok(resultService.getLeaderboard(quizId));
    }

    @GetMapping("/api/result/user/{userId}")
    public ResponseEntity<List<Result>> getMyResults(@PathVariable Long userId) {
        return ResponseEntity.ok(resultService.getMyResults(userId));
    }
}

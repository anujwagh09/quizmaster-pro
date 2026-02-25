package com.quizmaster.dto;
	
import lombok.Data;

@Data
public class SubmitResponseDTO {
    private Integer score;
    private Integer totalQuestions;
    private Integer timeTaken;
    private String message;
}
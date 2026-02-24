package com.quizmaster.dto;

import lombok.Data;

@Data
public class QuestionResponseDTO {
    private Long id;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}
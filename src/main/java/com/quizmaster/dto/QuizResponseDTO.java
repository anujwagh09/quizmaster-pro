package com.quizmaster.dto;

import lombok.Data;

@Data
public class QuizResponseDTO {
    private Long id;
    private String title;
    private String topic;
    private Integer duration;
}
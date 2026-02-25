package com.quizmaster.dto;

import lombok.Data;
import java.util.Map;

@Data
public class SubmitRequestDTO {
    private Long quizId;
    private Long userId;
    private Integer timeTaken;              
    private Map<Long, String> answers;     
}
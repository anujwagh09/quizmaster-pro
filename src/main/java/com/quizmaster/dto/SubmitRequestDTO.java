package com.quizmaster.dto;

import lombok.Data;
import java.util.List;

@Data
public class SubmitRequestDTO {

    private Long quizId;
    private Long userId;
    private Integer timeTaken;
    private List<AnswerDTO> answers;   
    @Data
    public static class AnswerDTO {
        private Long questionId;
        private String selectedOption;
    }
}

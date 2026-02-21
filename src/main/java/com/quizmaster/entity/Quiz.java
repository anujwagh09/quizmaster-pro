package com.quizmaster.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Topic is required")
    private String topic;

    @NotNull(message = "Duration is required")
    private Integer duration;   

    private LocalDateTime createdAt;

}

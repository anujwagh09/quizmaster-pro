package com.quizmaster.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer score;

	private Integer totalQuestions;

	private Integer timeTaken; 

	private LocalDateTime submittedAt;
	@PrePersist
	public void prePersist() {
        this.submittedAt = LocalDateTime.now();
    }
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;
}

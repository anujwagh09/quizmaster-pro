package com.quizmaster.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, columnDefinition = "TEXT")
	private String questionText;
	@Column(nullable = false)
	private String optionA;
	@Column(nullable = false)
	private String optionB;
	@Column(nullable = false)
	private String optionC;
	@Column(nullable = false)
	private String optionD;
	@Column(nullable = false)
	private String correctAnswer;
	@ManyToOne
    @JoinColumn(name = "quiz_id",nullable = false)
    private Quiz quiz;

}

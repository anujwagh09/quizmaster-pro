package com.quizmaster.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizmaster.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	

}

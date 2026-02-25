package com.quizmaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizmaster.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
	List<Result> findByQuizIdOrderByScoreDescTimeTakenAsc(Long quizId);
	List<Result> findByUserId(Long userId);
}

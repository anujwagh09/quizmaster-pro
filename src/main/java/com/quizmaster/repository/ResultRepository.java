package com.quizmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizmaster.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

}

package com.quizmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizmaster.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}

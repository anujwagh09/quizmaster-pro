package com.quizmaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizmaster.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

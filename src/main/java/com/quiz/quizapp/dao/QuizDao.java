package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface QuizDao extends JpaRepository<Quiz, UUID> {
    @Query("SELECT c FROM Category c WHERE c.cid = :id")
    Quiz findByQuizId(@Param("id") UUID id);
}

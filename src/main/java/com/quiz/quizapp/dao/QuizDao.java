package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface QuizDao extends JpaRepository<Quiz, UUID> {
    Quiz findByQuizId(UUID quizId);
}

package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.UserQuizAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuizDao extends JpaRepository<UserQuizAssociation, Integer> {
}

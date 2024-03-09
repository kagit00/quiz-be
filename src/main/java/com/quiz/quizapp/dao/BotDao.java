package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.BotDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotDao extends JpaRepository<BotDetails, Integer> {
}

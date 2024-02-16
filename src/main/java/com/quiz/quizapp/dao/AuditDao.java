package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditDao extends JpaRepository<Audit, Long> {
}

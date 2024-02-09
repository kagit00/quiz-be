package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}

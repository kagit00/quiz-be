package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Role dao.
 */
public interface RoleDao extends JpaRepository<Role, Long> {
}

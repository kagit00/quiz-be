package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface User dao.
 */
public interface UserDao extends JpaRepository<User, Long> {
    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);
}

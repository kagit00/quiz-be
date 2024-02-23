package com.quiz.quizapp.service;

import com.quiz.quizapp.model.User;
import com.quiz.quizapp.model.UserQuizAssociation;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Register user user.
     *
     * @param user the user
     * @return the user
     */
    User registerUser(User user);

    /**
     * Gets user by username.
     *
     * @param username the username
     * @return the user by username
     */
    User getUserByUsername(String username);

    /**
     * Update user by username user.
     *
     * @param username the username
     * @param user     the user
     * @return the user
     */
    User updateUserByUsername(String username, User user);

    /**
     * Delete user by username.
     *
     * @param username the username
     */
    void deleteUserByUsername(String username);
    UserQuizAssociation saveUserQuizScore(UserQuizAssociation userQuizAssociation);
}

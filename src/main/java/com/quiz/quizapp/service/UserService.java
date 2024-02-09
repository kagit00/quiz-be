package com.quiz.quizapp.service;

import com.quiz.quizapp.model.User;

public interface UserService {
    /**
     * The function "registerUser" registers a user with the given information and assigns them the
     * specified roles.
     * 
     * @param user The user object contains the information of the user who is registering. This can
     * include attributes such as username, password, email, and any other relevant information.
     * @return The method registerUser returns a User object.
     */
    User registerUser(User user);
    /**
     * The function getUserByUsername returns a User object based on the given username.
     * 
     * @param username A string representing the username of the user.
     * @return The method is returning a User object.
     */
    User getUserByUsername(String username);
    User updateUserByUsername(String username, User user);
    void deleteUserByUsername(String username);
}

package com.quiz.quizapp.cache;

import com.quiz.quizapp.dao.UserDao;
import com.quiz.quizapp.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * The type Cache.
 */
@Component
public class Cache {
    private final UserDao userDao;


    /**
     * Instantiates a new Cache.
     *
     * @param userDao the user dao
     */
    public Cache(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * Gets user by username.
     *
     * @param username the username
     * @return the user by username
     */
    @Cacheable(value = "cache", key = "#username", unless = "#result == null")
    public User getUserByUsername(String username) {
        return this.userDao.findByUsername(username);
    }
}

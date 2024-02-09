package com.quiz.quizapp.cache;

import com.quiz.quizapp.dao.UserDao;
import com.quiz.quizapp.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class Cache {
    private final UserDao userDao;

    public Cache(UserDao userDao) {
        this.userDao = userDao;
    }

    @Cacheable(value = "cache", key = "#username", unless = "#result == null")
    public User getUserByUsername(String username) {
        return this.userDao.findByUsername(username);
    }
}

package com.quiz.quizapp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoTest {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;

    @Test
    void findByUsername() {
        assertNull(userDao.findByUsername("ghhh12"), "User Dao works");
    }
}
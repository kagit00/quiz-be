package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.QuizFilterParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.LinkedHashSet;


@SpringBootTest
class QuizCustomDaoTest {

    @Autowired
    private QuizCustomDao quizCustomDao;

    @Test
    void findFilteredQuizzes() {
        QuizFilterParams quizFilterParams = new QuizFilterParams("Test", "Test Quiz", new LinkedHashSet<>());
        Assertions.assertEquals(0, quizCustomDao.findFilteredQuizzes(quizFilterParams).size(), "Quiz Custom Dao works Fine");
    }
}
package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Category;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.QuizFilterParams;
import com.quiz.quizapp.util.DefaultValuesPopulator;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

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
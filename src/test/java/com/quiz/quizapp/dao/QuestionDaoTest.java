package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Category;
import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.util.DefaultValuesPopulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionDaoTest {
    @Autowired
    private QuestionDao questionDao;

    @Test
    void findByQuiz() {
        Quiz quiz = new Quiz(
                DefaultValuesPopulator.getRandomId(),
                false,
                "Test Quiz",
                "This record has been created for testing purpose only",
                0,
                0,
                "",
                new Category(),
                new LinkedHashSet<>()
        );
        Assertions.assertEquals(Collections.EMPTY_LIST, questionDao.findByQuiz(quiz), "Question Dao Works fine");
    }

    @Test
    void findByQuestionId() {
        assertNull(questionDao.findByQuestionId(DefaultValuesPopulator.getRandomId()), "Question Dao Works fine");
    }
}
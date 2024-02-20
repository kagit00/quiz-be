package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.QuizFilterParams;
import com.quiz.quizapp.model.Quiz;

import java.util.Set;

/**
 * The interface Quiz custom dao.
 */
public interface QuizCustomDao {
    /**
     * Find filtered quizzes set.
     *
     * @param quizFilterParams the quiz filter params
     * @return the set
     */
    Set<Quiz> findFilteredQuizzes(QuizFilterParams quizFilterParams);
}

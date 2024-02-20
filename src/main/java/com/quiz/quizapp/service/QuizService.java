package com.quiz.quizapp.service;

import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.QuizFilterParams;

import java.util.Set;
import java.util.UUID;

public interface QuizService {
    Quiz addQuiz(Quiz quiz);
    Quiz updateQuiz(Quiz quiz);
    Set<Quiz> getQuizzes(QuizFilterParams quizFilterParams);
    Quiz getQuiz(UUID id);
    void deleteQuiz(UUID id);
}

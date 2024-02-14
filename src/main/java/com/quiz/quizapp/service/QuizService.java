package com.quiz.quizapp.service;

import com.quiz.quizapp.model.Quiz;
import java.util.Set;
import java.util.UUID;

public interface QuizService {
    Quiz addQuiz(Quiz quiz);
    Quiz updateQuiz(Quiz quiz);
    Set<Quiz> getQuizzes();
    Quiz getQuiz(UUID id);
    void deleteQuiz(UUID id);
}

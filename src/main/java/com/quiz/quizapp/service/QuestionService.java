package com.quiz.quizapp.service;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.Quiz;

import java.util.Set;
import java.util.UUID;

public interface QuestionService {
    Question addQuestion(Question q);
    Question updateQuestion(Question q);
    Set<Question> getQuestions();
    Question getQuestion(UUID id);
    void deleteQuiz(UUID id);
    Set<Question> getQuestionsOfQuiz(Quiz quiz);
}

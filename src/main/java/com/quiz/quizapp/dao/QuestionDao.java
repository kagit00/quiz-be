package com.quiz.quizapp.dao;


import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface QuestionDao extends JpaRepository<Question, UUID> {
    List<Question> findByQuiz(Quiz quiz);
    Question findByQuestionId(UUID id);
}
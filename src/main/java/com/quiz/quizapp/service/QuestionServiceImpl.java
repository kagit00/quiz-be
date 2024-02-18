package com.quiz.quizapp.service;

import com.quiz.quizapp.cache.Cache;
import com.quiz.quizapp.dao.QuestionDao;
import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.Quiz;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final Cache cache;
    private final QuestionDao questionDao;

    public QuestionServiceImpl(Cache cache, QuestionDao questionDao) {
        this.cache = cache;
        this.questionDao = questionDao;
    }
    @Override
    public Question addQuestion(Question q) {
        Question question = this.cache.getQuestionById(q.getQuestionId());
        if (!Objects.isNull(question))
            throw new BadRequestException("Question already exists");
        return this.questionDao.save(q);
    }

    @Override
    public Question updateQuestion(Question q) {
        Question question = this.cache.getQuestionById(q.getQuestionId());
        if (Objects.isNull(question))
            throw new BadRequestException("Question doesn't exist");
        if (!question.getContent().equals(q.getContent()))
            question.setContent(q.getContent());
        if (!question.getCorrectAnswer().equals(q.getCorrectAnswer()))
            question.setCorrectAnswer(q.getCorrectAnswer());
        if (!StringUtils.isEmpty(q.getImageUrl()) && !question.getImageUrl().equals(q.getImageUrl()))
            question.setImageUrl(q.getImageUrl());
        return this.questionDao.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return this.cache.getQuestions();
    }

    @Override
    public Question getQuestion(UUID id) {
        return this.cache.getQuestionById(id);
    }

    @Override
    public void deleteQuiz(UUID id) {
        Question question = this.cache.getQuestionById(id);
        if (Objects.isNull(question))
            throw new BadRequestException("Question doesn't exist");
        this.questionDao.delete(question);
    }

    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
        return this.cache.getQuestionByQuiz(quiz);
    }
}

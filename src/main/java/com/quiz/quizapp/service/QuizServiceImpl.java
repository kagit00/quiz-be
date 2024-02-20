package com.quiz.quizapp.service;

import com.quiz.quizapp.cache.Cache;
import com.quiz.quizapp.dao.QuizCustomDaoImpl;
import com.quiz.quizapp.dao.QuizDao;
import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.QuizFilterParams;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * The type Quiz service.
 */
@Service
public class QuizServiceImpl implements QuizService {

    private final Cache cache;
    private final QuizDao quizDao;
    private final QuizCustomDaoImpl quizCustomDao;

    /**
     * Instantiates a new Quiz service.
     *
     * @param cache         the cache
     * @param quizDao       the quiz dao
     * @param quizCustomDao the quiz custom dao
     */
    public QuizServiceImpl(Cache cache, QuizDao quizDao, QuizCustomDaoImpl quizCustomDao) {
        this.cache = cache;
        this.quizDao = quizDao;
        this.quizCustomDao = quizCustomDao;
    }

    @Override
    public Quiz addQuiz(Quiz quiz) {
        Quiz existingQuiz = this.cache.getQuizById(quiz.getQuizId());
        if (!Objects.isNull(existingQuiz))
            throw new BadRequestException("Quiz already exists");
        return this.quizDao.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        Quiz existingQuiz = this.cache.getQuizById(quiz.getQuizId());
        if (Objects.isNull(existingQuiz))
            throw new BadRequestException("Quiz doesn't exist");
        if (!existingQuiz.getDescription().equals(quiz.getDescription()))
            existingQuiz.setDescription(quiz.getDescription());
        if (existingQuiz.getMaxMarks() != quiz.getMaxMarks())
            existingQuiz.setMaxMarks(quiz.getMaxMarks());
        if (!existingQuiz.getTitle().equals(quiz.getTitle()))
            existingQuiz.setTitle(quiz.getTitle());
        if (existingQuiz.getNumberOfQuestions() != quiz.getNumberOfQuestions())
            existingQuiz.setNumberOfQuestions(quiz.getNumberOfQuestions());
        return this.quizDao.save(existingQuiz);
    }

    @Override
    public Set<Quiz> getQuizzes(QuizFilterParams quizFilterParams) {
        if (!ObjectUtils.isEmpty(quizFilterParams)) {
            return quizCustomDao.findFilteredQuizzes(quizFilterParams);
        }
        return this.cache.getQuizzes();
    }

    @Override
    public Quiz getQuiz(UUID id) {
        return this.cache.getQuizById(id);
    }

    @Override
    public void deleteQuiz(UUID id) {
        Quiz existingQuiz = this.cache.getQuizById(id);
        if (Objects.isNull(existingQuiz))
            throw new BadRequestException("Quiz doesn't exist");
        this.quizDao.delete(existingQuiz);
    }
}

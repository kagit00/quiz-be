package com.quiz.quizapp.cache;

import com.quiz.quizapp.dao.CategoryDao;
import com.quiz.quizapp.dao.QuestionDao;
import com.quiz.quizapp.dao.QuizDao;
import com.quiz.quizapp.dao.UserDao;
import com.quiz.quizapp.model.Category;
import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The type Cache.
 */
@Component
public class Cache {
    private final UserDao userDao;
    private final CategoryDao categoryDao;
    private final QuizDao quizDao;
    private final QuestionDao questionDao;

    /**
     * Instantiates a new Cache.
     *
     * @param userDao the user dao
     */
    public Cache(UserDao userDao, CategoryDao categoryDao, QuizDao quizDao, QuestionDao questionDao) {
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }


    /**
     * Gets user by username.
     *
     * @param username the username
     * @return the user by username
     */
    @Cacheable(value = "cache", key = "#username", unless = "#result == null")
    public User getUserByUsername(String username) {
        return this.userDao.findByUsername(username);
    }

    @Cacheable(value = "cache", key = "category_" + "#id", unless = "#result == null")
    public Category getCategoryById(UUID id) {
        return this.categoryDao.findByCid(id);
    }

    @Cacheable(value = "cache", key = "allCategory", unless = "#result == null")
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(this.categoryDao.findAll());
    }

    @Cacheable(value = "cache", key = "quiz_" + "#id", unless = "#result == null")
    public Quiz getQuizById(UUID id) {
        return this.quizDao.findByQuizId(id);
    }

    @Cacheable(value = "cache", key = "allQuiz", unless = "#result == null")
    public Set<Quiz> getQuizzes() {
        return new LinkedHashSet<>(this.quizDao.findAll());
    }

    @Cacheable(value = "cache", key = "question_" + "#id", unless = "#result == null")
    public Question getQuestionById(UUID id) {
        return this.questionDao.findByQuestionId(id);
    }

    @Cacheable(value = "cache", key = "allQuestions", unless = "#result == null")
    public Set<Question> getQuestions() {
        return new LinkedHashSet<>(this.questionDao.findAll());
    }

    @Cacheable(value = "cache", key = "quiz" + "#id", unless = "#result == null")
    public Set<Question> getQuestionByQuiz(Quiz quiz) {
        return new LinkedHashSet<>(this.questionDao.findByQuiz(quiz));
    }
}

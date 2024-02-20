package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Category;
import com.quiz.quizapp.model.QuizFilterParams;
import com.quiz.quizapp.model.Quiz;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Quiz custom dao.
 */
@Repository
public class QuizCustomDaoImpl implements QuizCustomDao {
    private final EntityManager entityManager;

    /**
     * Instantiates a new Quiz custom dao.
     *
     * @param entityManager the entity manager
     */
    public QuizCustomDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Set<Quiz> findFilteredQuizzes(QuizFilterParams quizFilterParams) {
        CriteriaBuilder criteriaBuilder =  entityManager.getCriteriaBuilder();
        CriteriaQuery<Quiz> criteriaQuery = criteriaBuilder.createQuery(Quiz.class);
        Root<Quiz> root = criteriaQuery.from(Quiz.class);

        Predicate predicate = criteriaBuilder.conjunction();
        String titleStartsWith = quizFilterParams.getTitleStartsWith();
        String titleContains = quizFilterParams.getTitleContains();
        Set<Category> categories = quizFilterParams.getCategories();

        if (!StringUtils.isEmpty(titleStartsWith)) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("title"), titleStartsWith + "%"));
        }

        if (!StringUtils.isEmpty(titleContains)) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("title"), "%" + titleContains + "%"));
        }

        if (!ObjectUtils.isEmpty(categories) && !categories.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, root.get("category").in(categories));
        }

        criteriaQuery.select(root).where(predicate);
        return new LinkedHashSet<>(entityManager.createQuery(criteriaQuery).getResultList());
    }
}

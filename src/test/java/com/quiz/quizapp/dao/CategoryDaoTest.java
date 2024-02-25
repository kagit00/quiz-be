package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Category;
import com.quiz.quizapp.util.DefaultValuesPopulator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.LinkedHashSet;

/**
 * The type Category dao test.
 */
@SpringBootTest
class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * Find by cid.
     */
    @Test
    void findByCid() {
        Category category = new Category(DefaultValuesPopulator.getRandomId(), "Testing", "This record is created only for application testing", new LinkedHashSet<>());
        categoryDao.save(category);
        Assertions.assertEquals("Testing", categoryDao.findByCid(category.getCid()).getTitle(), "Category Dao works fine");
    }
}
package com.quiz.quizapp.service;

import com.quiz.quizapp.cache.Cache;
import com.quiz.quizapp.dao.CategoryDao;
import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.model.Category;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final Cache cache;
    private final CategoryDao categoryDao;

    public CategoryServiceImpl(Cache cache, CategoryDao categoryDao) {
        this.cache = cache;
        this.categoryDao = categoryDao;
    }

    @Override
    public Category addCategory(Category category) {
        Category existingCategory = this.cache.getCategoryById(category.getCid());
        if (!Objects.isNull(existingCategory))
            throw new BadRequestException("Category already exists");
        return this.categoryDao.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category existingCategory = this.cache.getCategoryById(category.getCid());
        if (Objects.isNull(existingCategory))
            throw new BadRequestException("Category doesn't exist");
        if (!existingCategory.getDescription().equals(category.getDescription()))
            existingCategory.setDescription(category.getDescription());
        if (!existingCategory.getTitle().equals(category.getTitle()))
            existingCategory.setTitle(category.getTitle());
        return this.categoryDao.save(existingCategory);
    }

    @Override
    public Set<Category> getCategories() {
        return this.cache.getCategories();
    }

    @Override
    public Category getCategory(UUID id) {
        return this.cache.getCategoryById(id);
    }

    @Override
    public void deleteCategory(UUID id) {
        Category existingCategory = this.cache.getCategoryById(id);
        if (Objects.isNull(existingCategory))
            throw new BadRequestException("Category doesn't exist");
        this.categoryDao.delete(existingCategory);
    }
}

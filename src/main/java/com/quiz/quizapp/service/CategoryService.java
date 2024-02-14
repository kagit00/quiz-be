package com.quiz.quizapp.service;

import com.quiz.quizapp.model.Category;

import java.util.Set;
import java.util.UUID;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Category category);
    Set<Category> getCategories();
    Category getCategory(UUID id);
    void deleteCategory(UUID id);
}

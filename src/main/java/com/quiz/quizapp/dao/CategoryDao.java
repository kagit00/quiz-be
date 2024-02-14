package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryDao extends JpaRepository<Category, UUID> {
    Category findByCid(UUID id);
}

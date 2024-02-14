package com.quiz.quizapp.controllers;

import com.quiz.quizapp.model.Category;
import com.quiz.quizapp.model.NoContent;
import com.quiz.quizapp.model.Success;
import com.quiz.quizapp.service.CategoryServiceImpl;
import com.quiz.quizapp.util.BasicUtility;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> addCategory(@Valid @RequestBody Category category) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.categoryService.addCategory(category)), HttpStatus.OK);
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> updateCategory(@Valid @RequestBody Category category) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.categoryService.updateCategory(category)), HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> getCategories() {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.categoryService.getCategories()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> getCategoryById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.categoryService.getCategory(id)), HttpStatus.OK);
    }

    public ResponseEntity<Success> deleteCategory(@PathVariable("id") UUID id) {
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(BasicUtility.setSuccessBody(new NoContent(HttpStatus.OK, "Category Deleted Successfully")), HttpStatus.OK);
    }
}

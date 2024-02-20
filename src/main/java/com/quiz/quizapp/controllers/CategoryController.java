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

/**
 * The type Category controller.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    /**
     * Instantiates a new Category controller.
     *
     * @param categoryService the category service
     */
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Add category response entity.
     *
     * @param category the category
     * @return the response entity
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> addCategory(@Valid @RequestBody Category category) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.categoryService.addCategory(category)), HttpStatus.OK);
    }

    /**
     * Update category response entity.
     *
     * @param id       the id
     * @param category the category
     * @return the response entity
     */
    @PutMapping(value = "/{cid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> updateCategory(@PathVariable("cid") UUID id, @Valid @RequestBody Category category) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.categoryService.updateCategory(category)), HttpStatus.OK);
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> getCategories() {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.categoryService.getCategories()), HttpStatus.OK);
    }

    /**
     * Gets category by id.
     *
     * @param id the id
     * @return the category by id
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> getCategoryById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.categoryService.getCategory(id)), HttpStatus.OK);
    }

    /**
     * Delete category response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(value = "/{cid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> deleteCategory(@PathVariable("cid") UUID id) {
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(BasicUtility.setSuccessBody(new NoContent(HttpStatus.OK, "Category Deleted Successfully")), HttpStatus.OK);
    }
}

package com.quiz.quizapp.controllers;

import com.quiz.quizapp.model.NoContent;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.QuizFilterParams;
import com.quiz.quizapp.model.Success;
import com.quiz.quizapp.service.QuizServiceImpl;
import com.quiz.quizapp.util.BasicUtility;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The type Quiz controller.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizServiceImpl quizService;

    /**
     * Instantiates a new Quiz controller.
     *
     * @param quizService the quiz service
     */
    public QuizController(QuizServiceImpl quizService) {
        this.quizService = quizService;
    }

    /**
     * Add quiz response entity.
     *
     * @param quiz the quiz
     * @return the response entity
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> addQuiz(@Valid @RequestBody Quiz quiz) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.quizService.addQuiz(quiz)), HttpStatus.OK);
    }

    /**
     * Update quiz response entity.
     *
     * @param quiz the quiz
     * @return the response entity
     */
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> updateQuiz(@Valid @RequestBody Quiz quiz) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.quizService.updateQuiz(quiz)), HttpStatus.OK);
    }

    /**
     * Find quiz by quiz identifier response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> findQuizByQuizIdentifier(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.quizService.getQuiz(id)), HttpStatus.OK);
    }

    /**
     * Gets all quizzes.
     *
     * @param quizFilterParams the quiz filter params
     * @return the all quizzes
     */
    @PostMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> getAllQuizzes(@RequestBody QuizFilterParams quizFilterParams) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.quizService.getQuizzes(quizFilterParams)), HttpStatus.OK);
    }

    /**
     * Delete quiz response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> deleteQuiz(@PathVariable("id") UUID id) {
        this.quizService.deleteQuiz(id);
        return new ResponseEntity<>(BasicUtility.setSuccessBody(new NoContent(HttpStatus.OK, "Quiz Deleted Successfully")), HttpStatus.OK);
    }
}

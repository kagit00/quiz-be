package com.quiz.quizapp.controllers;

import com.quiz.quizapp.cache.Cache;
import com.quiz.quizapp.model.NoContent;
import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.Success;
import com.quiz.quizapp.service.QuestionServiceImpl;
import com.quiz.quizapp.util.BasicUtility;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The type Question controller.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionServiceImpl questionService;
    private final Cache cache;

    /**
     * Instantiates a new Question controller.
     *
     * @param questionService the question service
     * @param cache           the cache
     */
    public QuestionController(QuestionServiceImpl questionService, Cache cache) {
        this.questionService = questionService;
        this.cache = cache;
    }

    /**
     * Add question response entity.
     *
     * @param question the question
     * @return the response entity
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> addQuestion(@Valid @RequestBody Question question) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.questionService.addQuestion(question)), HttpStatus.OK);
    }

    /**
     * Update question response entity.
     *
     * @param question the question
     * @return the response entity
     */
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> updateQuestion(@Valid @RequestBody Question question) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.questionService.updateQuestion(question)), HttpStatus.OK);
    }

    /**
     * Find question by q id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> findQuestionByQId(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.questionService.getQuestion(id)), HttpStatus.OK);
    }

    /**
     * Gets all questions.
     *
     * @return the all questions
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> getAllQuestions() {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.questionService.getQuestions()), HttpStatus.OK);
    }

    /**
     * Gets all questions by quiz.
     *
     * @param id the id
     * @return the all questions by quiz
     */
    @GetMapping(value = "/quiz/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> getAllQuestionsByQuiz(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.questionService.getQuestionsOfQuiz(cache.getQuizById(id))), HttpStatus.OK);
    }

    /**
     * Delete quiz response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> deleteQuiz(@PathVariable("id") UUID id) {
        this.questionService.deleteQuiz(id);
        return new ResponseEntity<>(BasicUtility.setSuccessBody(new NoContent(HttpStatus.OK, "Quiz Deleted Successfully")), HttpStatus.OK);
    }
}

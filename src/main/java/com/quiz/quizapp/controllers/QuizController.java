package com.quiz.quizapp.controllers;

import com.quiz.quizapp.model.NoContent;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.Success;
import com.quiz.quizapp.service.QuizServiceImpl;
import com.quiz.quizapp.util.BasicUtility;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizServiceImpl quizService;

    public QuizController(QuizServiceImpl quizService) {
        this.quizService = quizService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> addQuiz(@Valid @RequestBody Quiz quiz) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.quizService.addQuiz(quiz)), HttpStatus.OK);
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> updateQuiz(@Valid @RequestBody Quiz quiz) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.quizService.updateQuiz(quiz)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> findQuizByQuizIdentifier(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.quizService.getQuiz(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> getAllQuizzes() {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.quizService.getQuizzes()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> deleteQuiz(@PathVariable("id") UUID id) {
        this.quizService.deleteQuiz(id);
        return new ResponseEntity<>(BasicUtility.setSuccessBody(new NoContent(HttpStatus.OK, "Quiz Deleted Successfully")), HttpStatus.OK);
    }
}

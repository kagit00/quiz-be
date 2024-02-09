package com.quiz.quizapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Bad request exception.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    /**
     * Instantiates a new Bad request exception.
     *
     * @param m the m
     */
    public BadRequestException(String m) {
        super(m);
    }
}

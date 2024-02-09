package com.quiz.quizapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Internal server error exception.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {
    /**
     * Instantiates a new Internal server error exception.
     *
     * @param m the m
     */
    public InternalServerErrorException(String m) {
        super(m);
    }
}

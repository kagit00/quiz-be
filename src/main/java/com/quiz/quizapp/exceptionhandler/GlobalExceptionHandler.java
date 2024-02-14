package com.quiz.quizapp.exceptionhandler;

import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.exception.InternalServerErrorException;
import com.quiz.quizapp.model.Error;
import com.quiz.quizapp.util.BasicUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The type Global exception handler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This function handles a BadRequestException by returning a ResponseEntity with an Error object
     * containing the exception message, HttpStatus, timestamp, and UID.
     *
     * @param e The parameter "e" is an instance of the BadRequestException class, which is an exception that is thrown when a bad request is made.
     * @return A ResponseEntity object is being returned.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(BasicUtility.getError(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle validation exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder("Invalid request parameters:");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(" Field '").append(fieldError.getField())
                    .append("' ").append(fieldError.getDefaultMessage()).append(";");
        }
        return new ResponseEntity<>(BasicUtility.getError(errorMessage.toString(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    /**
     * This function handles an InternalServerErrorException and returns a ResponseEntity with an Error
     * object containing the exception message, status code, timestamp, and unique identifier.
     *
     * @param e The parameter "e" is an instance of the InternalServerErrorException class, which is an exception that is thrown when an internal server error occurs.
     * @return A ResponseEntity object is being returned.
     */
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Error> handleInternalServerErrorException(InternalServerErrorException e) {
        return new ResponseEntity<>(BasicUtility.getError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

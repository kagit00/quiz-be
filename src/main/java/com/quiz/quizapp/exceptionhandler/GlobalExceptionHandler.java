package com.quiz.quizapp.exceptionhandler;

import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.exception.InternalServerErrorException;
import com.quiz.quizapp.model.Error;
import com.quiz.quizapp.util.DefaultValuesPopulator;
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
        return new ResponseEntity<>(getError(e.getMessage(), HttpStatus.BAD_REQUEST, DefaultValuesPopulator.getCurrentTimestamp(), DefaultValuesPopulator.getUid()), HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>(getError(errorMessage.toString(), HttpStatus.BAD_REQUEST, DefaultValuesPopulator.getCurrentTimestamp(), DefaultValuesPopulator.getUid()), HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>(getError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, DefaultValuesPopulator.getCurrentTimestamp(), DefaultValuesPopulator.getUid()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * The function creates and returns an Error object with the specified error message, status,
     * timestamp, and uid.
     * 
     * @param errorMsg The errorMsg parameter is a String that represents the error message. It is the
     * message that describes the error that occurred.
     * @param status The status parameter is of type HttpStatus, which is an enumeration representing
     * HTTP status codes. It is used to indicate the status of the error, such as 404 for "Not Found"
     * or 500 for "Internal Server Error".
     * @param timestamp The timestamp parameter is a string that represents the timestamp of when the
     * error occurred. It can be in any format that is suitable for your application, such as
     * "yyyy-MM-dd HH:mm:ss" or "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'".
     * @param uid The uid parameter is a unique identifier for the error. It can be used to track and
     * identify specific errors in a system.
     * @return An instance of the Error class is being returned.
     */
    private Error getError(String errorMsg, HttpStatus status, String timestamp, String uid) {
        Error error = new Error();
        error.setErrorMsg(errorMsg);
        error.setStatus(status);
        error.setTimestamp(timestamp);
        error.setUid(uid);
        return error;
    }
}

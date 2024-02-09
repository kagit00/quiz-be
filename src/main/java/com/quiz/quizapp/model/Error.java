package com.quiz.quizapp.model;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * The type Error.
 */
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class Error {
    private String uid;
    private HttpStatus status;
    private String timestamp;
    private String errorMsg;
}

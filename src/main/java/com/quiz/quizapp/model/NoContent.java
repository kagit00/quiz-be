package com.quiz.quizapp.model;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * The type No content.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoContent {
    private HttpStatus status;
    private String msg;
}

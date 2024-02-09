package com.quiz.quizapp.model;

import lombok.*;

/**
 * The type Jwt request.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    private String username;
    private String password;
}

package com.quiz.quizapp.model;

import lombok.*;


/**
 * The type Jwt response.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class JwtResponse {
    private String token;
}

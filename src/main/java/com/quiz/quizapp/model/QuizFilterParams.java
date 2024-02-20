package com.quiz.quizapp.model;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Quiz filter params.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizFilterParams {
    private String titleStartsWith;
    private String titleContains;
    private Set<Category> categories = new LinkedHashSet<>();
}

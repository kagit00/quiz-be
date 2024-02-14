package com.quiz.quizapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuestionOptionsValidator.class)
public @interface ValidOptions {
    String message() default "Invalid Question Options";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

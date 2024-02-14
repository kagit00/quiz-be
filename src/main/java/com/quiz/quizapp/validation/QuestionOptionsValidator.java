package com.quiz.quizapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class QuestionOptionsValidator implements ConstraintValidator<ValidOptions, List<String>>  {
    @Override
    public void initialize(ValidOptions constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value.isEmpty()) return false;
        if (value.size() < 4) return false;
        for (String x : value) {
            if (StringUtils.isEmpty(x)) return false;
        }
        return true;
    }
}

package com.quiz.quizapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class FirstNameValidator implements ConstraintValidator<ValidFirstName, String> {
    /**
     * The function checks if a given string is a valid first name by ensuring it contains only
     * letters, hyphens, and apostrophes and is between 1 and 50 characters long.
     * 
     * @param value The value parameter represents the input string that needs to be validated. In this
     * case, it is used to validate a first name.
     * @param context The `context` parameter is an instance of `ConstraintValidatorContext` which
     * provides contextual information and operations for the constraint validation. It allows you to
     * customize the error messages and add additional validation constraints.
     * @return The method is returning a boolean value.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !StringUtils.isEmpty(value) && value.matches("^[a-zA-Z'-]{1,50}$");
    }
}

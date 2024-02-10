package com.quiz.quizapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * The type Last name validator.
 */
public class LastNameValidator implements ConstraintValidator<ValidLastName, String> {
    /**
     * The function checks if a given string is a valid user name, and throws a BadRequestException if
     * it is not.
     * 
     * @param value The value parameter represents the user name that needs to be validated.
     * @param context The `context` parameter is an instance of `ConstraintValidatorContext` which
     * provides contextual information and operations for the constraint validation. It allows you to
     * customize the validation error messages and add additional validation constraints.
     * @return The method is returning a boolean value. If the given value is null, empty, or matches
     * the regular expression pattern "^[a-zA-Z'-]{1,50}$", then it returns true. Otherwise, it throws
     * a BadRequestException with a message indicating the invalid user name.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ((value == null || StringUtils.isEmpty(value)) || value.matches("^[a-zA-Z'-]{1,50}$"));
    }
}

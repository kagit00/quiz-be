package com.quiz.quizapp.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    /**
     * The function checks if a given email address is valid and throws a BadRequestException if it is
     * not.
     * 
     * @param value The value parameter represents the email address that needs to be validated.
     * @param context The `context` parameter is an object of type `ConstraintValidatorContext` which
     * provides contextual information and operations for the constraint validation. It allows you to
     * customize the error messages and add additional validation constraints if needed.
     * @return A boolean value is being returned.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !StringUtils.isEmpty(value) && value.matches("^[\\w.-]+@[\\w-]+(?:\\.[\\w-]+)*$");
    }
}

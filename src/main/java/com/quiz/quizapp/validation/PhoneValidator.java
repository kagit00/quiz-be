package com.quiz.quizapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * The type Phone validator.
 */
public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
    /**
     * The function checks if a given phone number is valid and throws a BadRequestException if it is
     * not.
     * 
     * @param value The value parameter represents the phone number that needs to be validated.
     * @param context The `context` parameter is an object of type `ConstraintValidatorContext` which
     * provides contextual information and operations for the constraint validation. It allows you to
     * customize the error messages and add additional validation constraints if needed.
     * @return The method is returning a boolean value.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !StringUtils.isEmpty(value) && value.matches("^\\d{10}$");
    }
}

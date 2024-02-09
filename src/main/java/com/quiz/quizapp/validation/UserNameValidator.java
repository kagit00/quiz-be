package com.quiz.quizapp.validation;

import org.apache.commons.lang3.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<ValidUser, String> {
    /**
     * The function checks if a given string is a valid user name by ensuring it is not null, not
     * empty, and matches a specific pattern.
     * 
     * @param value The value parameter represents the user name that needs to be validated.
     * @param context The `context` parameter is an object of type `ConstraintValidatorContext` which
     * provides contextual information and operations for the constraint validation. It allows you to
     * customize the error messages and add additional validation constraints if needed.
     * @return The method returns a boolean value. If the given value is not null, not empty, and
     * matches the regular expression "^[a-zA-Z0-9_-]{3,16}$", then it returns true. Otherwise, it
     * throws a BadRequestException with the message "Invalid User Name -> " followed by the value.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !StringUtils.isEmpty(value) && value.matches("^[a-zA-Z0-9_-]{3,16}$");
    }
}

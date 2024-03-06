package com.quiz.quizapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Valid user.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameValidator.class)
public @interface ValidUser {
    /**
     * Message string.
     *
     * @return the string
     */
// The `String message() default "Invalid User Name";` is defining the default error message that
    // will be displayed if the validation fails. In this case, if the user name is invalid, the error
    // message "Invalid User Name" will be shown.
    String message() default "Invalid User Name";

    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     */
// The `Class<?>[] groups() default {};` is defining an array of group classes that the validation
    // constraint belongs to.
    Class<?>[] groups() default {};

    /**
     * Payload class [ ].
     *
     * @return the class [ ]
     */
// The `Class<? extends Payload>[] payload() default {};` is defining an array of payload classes
    // that can be used to provide additional metadata about the validation constraint. The payload
    // classes can be used to categorize or group the validation constraints.
    Class<? extends Payload>[] payload() default {};
}
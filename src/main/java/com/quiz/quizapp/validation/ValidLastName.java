package com.quiz.quizapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Valid last name.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LastNameValidator.class)
public @interface ValidLastName {
    /**
     * Message string.
     *
     * @return the string
     */
// The `String message() default "Invalid Last Name";` is defining the default error message that
    // will be displayed when the validation fails for the annotated field or method. In this case, the
    // default error message is "Invalid Last Name".
    String message() default "Invalid Last Name";

    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     */
// The `Class<?>[] groups() default {};` is defining an array of group classes that the constraint
    // belongs to. In Java validation, groups are used to group related constraints together and apply
    // them selectively. By default, the `groups()` method returns an empty array, indicating that the
    // constraint does not belong to any specific group.
    Class<?>[] groups() default {};

    /**
     * Payload class [ ].
     *
     * @return the class [ ]
     */
// The `Class<? extends Payload>[] payload() default {};` is defining an array of payload classes
    // that can be used to provide additional metadata about the validation constraint. The payload
    // classes must implement the `Payload` interface.
    Class<? extends Payload>[] payload() default {};
}

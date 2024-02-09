package com.quiz.quizapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FirstNameValidator.class)
public @interface ValidFirstName {
    // The `String message() default "Invalid First Name";` is defining the default error message that
    // will be displayed if the validation fails for the annotated field or method. In this case, the
    // default error message is "Invalid First Name".
    String message() default "Invalid First Name";
    // The `Class<?>[] groups() default {};` is defining an array of group classes that the constraint
    // belongs to. In Java validation, groups are used to group related constraints together and apply
    // them selectively. By default, the `groups()` method returns an empty array, indicating that the
    // constraint does not belong to any specific group.
    Class<?>[] groups() default {};
    // The `Class<? extends Payload>[] payload() default {};` is defining an array of payload classes
    // that can be used to provide additional metadata about the validation constraint.
    Class<? extends Payload>[] payload() default {};
}
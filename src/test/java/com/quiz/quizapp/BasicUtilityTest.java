package com.quiz.quizapp;

import com.quiz.quizapp.model.Role;
import com.quiz.quizapp.util.BasicUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The type Basic utility test.
 */
class BasicUtilityTest {
    /**
     * The `stringifyObjectTest` function tests whether the `stringifyObject` method successfully
     * converts a `Role` object into a JSON string representation.
     */
    @Test
    void stringifyObjectTest() {
        Role role = new Role();
        role.setId(3);
        role.setRoleName("Moderator");
        Assertions.assertEquals("{\"id\":3,\"roleName\":\"Moderator\",\"roles\":[]}", BasicUtility.stringifyObject(role), "Object stringified successfully");
    }
}

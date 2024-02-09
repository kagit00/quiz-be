package com.quiz.quizapp.util;

import com.quiz.quizapp.model.Role;
import com.quiz.quizapp.model.User;
import com.quiz.quizapp.model.UserRole;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class DefaultValuesPopulator {
    // The `private DefaultValuesPopulator()` is a private constructor of the `DefaultValuesPopulator`
    // class. It is throwing an `UnsupportedOperationException` with the message "Operation not
    // supported". This is done to prevent the instantiation of the `DefaultValuesPopulator` class, as
    // it is a utility class that provides static methods and should not be instantiated.
    private DefaultValuesPopulator() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public static Set<UserRole> populateDefaultUserRoles(User user) {
        Role role = new Role();
        role.setRoleName("USER");
        role.setId(2L);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);
        return userRoles;
    }

    public static String getTimestamp() {
        return OffsetDateTime.now().toString();
    }

    public static String getUid() {
        return UUID.randomUUID().toString();
    }
}

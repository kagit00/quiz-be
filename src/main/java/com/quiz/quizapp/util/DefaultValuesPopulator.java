package com.quiz.quizapp.util;

import com.quiz.quizapp.model.Role;
import com.quiz.quizapp.model.User;
import com.quiz.quizapp.model.UserRole;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The type Default values populator.
 */
public final class DefaultValuesPopulator {
    private DefaultValuesPopulator() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    /**
     * Populate default user roles set.
     *
     * @param user the user
     * @return the set
     */
    public static Set<UserRole> populateDefaultUserRoles(User user) {
        Role role = new Role();
        role.setRoleName(Constant.USER_ROLE);
        role.setId(2L);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);
        return userRoles;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public static String getTimestamp() {
        return OffsetDateTime.now().toString();
    }

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public static String getUid() {
        return UUID.randomUUID().toString();
    }
}

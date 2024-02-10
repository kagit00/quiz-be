package com.quiz.quizapp.service;

import com.quiz.quizapp.cache.Cache;
import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Objects;

/**
 * The type User details service.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Cache cache;

    /**
     * Instantiates a new User details service.
     *
     * @param cache the cache
     */
    public UserDetailsServiceImpl(Cache cache) {
        this.cache = cache;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            User existingUser = cache.getUserByUsername(username);
            if (Objects.isNull(existingUser)) {
                throw new BadRequestException("User doesn't exist.");
            }
            return existingUser;
        } catch (UsernameNotFoundException e) {
            throw new BadRequestException("Invalid Username");
        }
    }
}

package com.quiz.quizapp.service;

import com.quiz.quizapp.cache.Cache;
import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User existingUser = cache.getUserByUsername(username);
        if (Objects.isNull(existingUser)) {
            logger.info("User doesn't exist.");
            throw new BadRequestException("User doesn't exist.");
        }
        return existingUser;
    }
}

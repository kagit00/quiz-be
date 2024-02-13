package com.quiz.quizapp.service;

import com.quiz.quizapp.cache.Cache;
import com.quiz.quizapp.dao.RoleDao;
import com.quiz.quizapp.dao.UserDao;
import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.model.User;
import com.quiz.quizapp.model.UserRole;
import com.quiz.quizapp.util.DefaultValuesPopulator;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Set;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Cache cache;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder encoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Instantiates a new User service.
     *
     * @param userDao the user dao
     * @param cache   the cache
     * @param roleDao the role dao
     * @param encoder the encoder
     */
    public UserServiceImpl(UserDao userDao, Cache cache, RoleDao roleDao, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.cache = cache;
        this.roleDao = roleDao;
        this.encoder = encoder;
    }

    @Override
    public User registerUser(User user) {
        User existingUser = cache.getUserByUsername(user.getUsername());
        if (!Objects.isNull(existingUser))
            throw new BadRequestException("User already exists.");
        user.setPassword(encoder.encode(user.getPassword()));
        Set<UserRole> userRoles = DefaultValuesPopulator.populateDefaultUserRoles(user);
        for (UserRole ur : userRoles) roleDao.save(ur.getRole());
        user.getRoles().addAll(userRoles);
        userDao.save(user);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        logger.info("Fetching user by {}", username);
        return cache.getUserByUsername(username);
    }

    @Override
    public User updateUserByUsername(String username, User user) {
        User existingUser = cache.getUserByUsername(username);
        if (Objects.isNull(existingUser))
            throw new BadRequestException("User doesn't exist.");
        if (!user.getUsername().equals(existingUser.getUsername()))
            existingUser.setUsername(user.getUsername());
        if (!user.getPhone().equals(existingUser.getPhone()))
            existingUser.setPhone(user.getPhone());
        if (!StringUtils.isEmpty(user.getAbout()) && !user.getAbout().equals(existingUser.getAbout()))
            existingUser.setAbout(user.getAbout());
        if (!user.getEmail().equals(existingUser.getEmail()))
            existingUser.setEmail(user.getEmail());
        if (!ObjectUtils.isEmpty(user.getDateOfBirth()) && ObjectUtils.notEqual(user.getDateOfBirth(), existingUser.getDateOfBirth()))
            existingUser.setDateOfBirth(user.getDateOfBirth());
        if (!user.getFirstName().equals(existingUser.getFirstName()))
            existingUser.setFirstName(user.getFirstName());
        if (!StringUtils.isEmpty(user.getLastName()) && !user.getLastName().equals(existingUser.getLastName()))
            existingUser.setLastName(user.getLastName());
        userDao.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUserByUsername(String username) {
        User existingUser = cache.getUserByUsername(username);
        if (Objects.isNull(existingUser))
            throw new BadRequestException("User doesn't exist.");
        userDao.delete(existingUser);
    }
}

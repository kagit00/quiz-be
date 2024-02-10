package com.quiz.quizapp.controllers;

import com.quiz.quizapp.model.NoContent;
import com.quiz.quizapp.model.User;
import com.quiz.quizapp.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserServiceImpl userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Register user response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@Validated @RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
    }

    /**
     * Gets user by username.
     *
     * @param username the username
     * @return the user by username
     */
    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    /**
     * Update user by username response entity.
     *
     * @param username the username
     * @param user     the user
     * @return the response entity
     */
    @PutMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUserByUsername(@PathVariable("username") String username, @Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserByUsername(username, user), HttpStatus.OK);
    }

    /**
     * Delete user by username response entity.
     *
     * @param username the username
     * @return the response entity
     */
    @DeleteMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoContent> deleteUserByUsername(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(new NoContent(HttpStatus.OK, "successfully deleted."), HttpStatus.OK);
    }
}

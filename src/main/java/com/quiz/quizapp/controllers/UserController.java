package com.quiz.quizapp.controllers;

import com.quiz.quizapp.model.NoContent;
import com.quiz.quizapp.model.Success;
import com.quiz.quizapp.model.User;
import com.quiz.quizapp.service.UserServiceImpl;
import com.quiz.quizapp.util.BasicUtility;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/users")
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
    public ResponseEntity<Success> registerUser(@Validated @RequestBody User user) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(userService.registerUser(user)), HttpStatus.OK);
    }

    /**
     * Gets user by username.
     *
     * @param username the username
     * @return the user by username
     */
    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(userService.getUserByUsername(username)), HttpStatus.OK);
    }

    /**
     * Update user by username response entity.
     *
     * @param username the username
     * @param user     the user
     * @return the response entity
     */
    @PutMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> updateUserByUsername(@PathVariable("username") String username, @Valid @RequestBody User user) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(userService.updateUserByUsername(username, user)), HttpStatus.OK);
    }

    /**
     * Delete user by username response entity.
     *
     * @param username the username
     * @return the response entity
     */
    @DeleteMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> deleteUserByUsername(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(BasicUtility.setSuccessBody(new NoContent(HttpStatus.OK, "User successfully deleted.")), HttpStatus.OK);
    }
}

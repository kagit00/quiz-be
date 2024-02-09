package com.quiz.quizapp.controllers;

import com.quiz.quizapp.model.NoContent;
import com.quiz.quizapp.model.User;
import com.quiz.quizapp.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * The function registers a user by validating their data against a JSON schema and then calling
     * the registerUser method in the userService class.
     * 
     * @param user The "user" parameter is an object of type User that is received in the request body.
     * It is annotated with @RequestBody to indicate that the data for this parameter should be
     * extracted from the request body.
     * @return The method is returning a User object.
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
    }

    /**
     * This function retrieves a user by their username from the userService.
     * 
     * @param username The "username" parameter is a path variable that is extracted from the URL path.
     * It is used to specify the username of the user whose information is being requested.
     * @return The method is returning a User object.
     */
    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @PutMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUserByUsername(@PathVariable("username") String username, @Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserByUsername(username, user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoContent> deleteUserByUsername(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(new NoContent(HttpStatus.NO_CONTENT, "successfully deleted."), HttpStatus.NO_CONTENT);
    }
}

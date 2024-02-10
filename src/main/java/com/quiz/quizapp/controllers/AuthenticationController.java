package com.quiz.quizapp.controllers;

import com.quiz.quizapp.model.JwtRequest;
import com.quiz.quizapp.model.JwtResponse;
import com.quiz.quizapp.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Authentication controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * Instantiates a new Authentication controller.
     *
     * @param authenticationService the authentication service
     */
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Generate token response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/token")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest request) {
        return new ResponseEntity<>(this.authenticationService.generateToken(request), HttpStatus.OK);
    }
}

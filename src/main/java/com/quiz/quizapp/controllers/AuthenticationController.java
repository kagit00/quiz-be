package com.quiz.quizapp.controllers;

import com.quiz.quizapp.model.JwtRequest;
import com.quiz.quizapp.model.Success;
import com.quiz.quizapp.service.AuthenticationService;
import com.quiz.quizapp.util.BasicUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * The type Authentication controller.
 */
@CrossOrigin("*")
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
    @PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> generateToken(@RequestBody JwtRequest request) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.authenticationService.generateToken(request)), HttpStatus.OK);
    }

    @GetMapping(value = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> getPrincipal(Principal principal) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.authenticationService.getPrincipal(principal.getName())), HttpStatus.OK);
    }
}

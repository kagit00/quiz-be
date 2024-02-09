package com.quiz.quizapp.service;

import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.model.JwtRequest;
import com.quiz.quizapp.model.JwtResponse;
import com.quiz.quizapp.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;

    public AuthenticationService(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public JwtResponse generateToken(JwtRequest request) {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (DisabledException e) {
            throw new BadRequestException("User Disabled" + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Bad Credentials" + e.getMessage());
        }
    }
}

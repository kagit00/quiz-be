package com.quiz.quizapp.filter;

import com.quiz.quizapp.exception.InternalServerErrorException;
import com.quiz.quizapp.model.Error;
import com.quiz.quizapp.util.BasicUtility;
import com.quiz.quizapp.util.DefaultValuesPopulator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Jwt authentication entry point.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        BasicUtility.printError(e.getMessage(), response);
    }
}

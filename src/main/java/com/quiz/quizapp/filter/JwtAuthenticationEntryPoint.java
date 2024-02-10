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
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        try {
            logger.error("Unauthorized error: {}", e.getMessage());
            response.setStatus(401);
            Error error = new Error(DefaultValuesPopulator.getUid(), HttpStatus.UNAUTHORIZED, DefaultValuesPopulator.getCurrentTimestamp(), "Access Denied. " + e.getMessage());
            String str = BasicUtility.stringifyObject(error);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = response.getWriter();
            writer.write(str);
        } catch (IOException ex) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}

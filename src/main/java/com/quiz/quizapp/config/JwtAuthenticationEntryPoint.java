package com.quiz.quizapp.config;

import com.quiz.quizapp.exception.InternalServerErrorException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        try {
            logger.error("Unauthorized error: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            PrintWriter writer = response.getWriter();
            writer.println("Access Denied " + e.getMessage());
        } catch (IOException ex) {
            logger.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}

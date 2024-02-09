package com.quiz.quizapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    /**
     * The function handles an unauthorized error by logging the error message, sending an unauthorized
     * response to the client, and printing an "Access Denied" message.
     * 
     * @param request The HttpServletRequest object represents the HTTP request made by the client to
     * the server. It contains information such as the request method, headers, parameters, and body.
     * @param response The response object represents the HTTP response that will be sent back to the
     * client. It is used to send error messages and other data back to the client.
     * @param e AuthenticationException - the exception that occurred during authentication.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", e.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        PrintWriter writer = response.getWriter();
        writer.println("Access Denied " + e.getMessage());
    }
}

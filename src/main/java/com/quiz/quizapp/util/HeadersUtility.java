package com.quiz.quizapp.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;
import java.util.Enumeration;

public final class HeadersUtility {
    private HeadersUtility() {
        throw new UnsupportedOperationException("Not supported");
    }

    /**
     * Extract request headers string.
     *
     * @param request the request
     * @return the string
     */
    public static String extractRequestHeaders(HttpServletRequest request) {
        StringBuilder req = new StringBuilder("Headers: ");
        Collections.list(request.getHeaderNames()).forEach(headerName ->
                req.append(headerName)
                        .append(": ").append(request.getHeader(headerName)).append("\n"));
        return req.toString();
    }

    /**
     * Extract response headers string.
     *
     * @param response the response
     * @return the string
     */
    public static String extractResponseHeaders(HttpServletResponse response) {
        StringBuilder headers = new StringBuilder("Headers: ");
        Enumeration<String> headerNames = Collections.enumeration(response.getHeaderNames());
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.append(headerName)
                    .append(": ").append(response.getHeader(headerName)).append("\n");
        }
        return headers.toString();
    }
}

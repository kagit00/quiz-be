package com.quiz.quizapp.util;

import com.quiz.quizapp.model.Error;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.io.PrintWriter;

public final class ErrorUtility {
    private static final Logger logger = LoggerFactory.getLogger(ErrorUtility.class);

    private ErrorUtility() {
        throw new UnsupportedOperationException("Not supported");
    }

    /**
     * Gets error.
     *
     * @param errorMsg the error msg
     * @param status   the status
     * @return the error
     */
    public static Error getError(String errorMsg, HttpStatus status) {
        Error error = new Error();
        error.setErrorMsg(errorMsg);
        error.setStatus(status);
        error.setTimestamp(DefaultValuesPopulator.getCurrentTimestamp());
        error.setUid(DefaultValuesPopulator.getUid());
        return error;
    }

    /**
     * Print error.
     *
     * @param ex       the ex
     * @param response the response
     */
    public static void printError(String ex, HttpServletResponse response) {
        Error error = getError(ex, HttpStatus.valueOf(response.getStatus()));
        String str = BasicUtility.stringifyObject(error);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(str);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}

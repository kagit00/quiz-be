package com.quiz.quizapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.model.Error;
import com.quiz.quizapp.model.Success;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Basic utility.
 */
public final class BasicUtility {

    private BasicUtility() {
        throw new UnsupportedOperationException("Not supported");
    }
    private static final Logger logger = LoggerFactory.getLogger(BasicUtility.class);


    /**
     * The function takes an object and converts it into a JSON string using the Jackson ObjectMapper.
     *
     * @param o The parameter "o" is an object that you want to convert into a JSON string.
     * @return The method is returning a string representation of the given object.
     */
    public static String stringifyObject(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Failed stringifying object");
        }
    }

    public static Success setSuccessBody(Object body) {
        Success success = new Success();
        success.setUid(DefaultValuesPopulator.getUid());
        success.setTimestamp(DefaultValuesPopulator.getCurrentTimestamp());
        success.setBody(body);
        return success;
    }

    public static Error getError(String errorMsg, HttpStatus status) {
        Error error = new Error();
        error.setErrorMsg(errorMsg);
        error.setStatus(status);
        error.setTimestamp(DefaultValuesPopulator.getCurrentTimestamp());
        error.setUid(DefaultValuesPopulator.getUid());
        return error;
    }

    public static void printError(String ex, HttpServletResponse response) {
        Error error = BasicUtility.getError(ex, HttpStatus.valueOf(500));
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

package com.quiz.quizapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.quizapp.exception.BadRequestException;

public final class BasicUtility {

    private BasicUtility() {
        throw new UnsupportedOperationException("Not supported");
    }

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
}

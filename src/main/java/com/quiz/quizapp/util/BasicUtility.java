package com.quiz.quizapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.quiz.quizapp.exception.BadRequestException;
import com.quiz.quizapp.model.Success;

/**
 * The type Basic utility.
 */
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

    /**
     * Sets success body.
     *
     * @param body the body
     * @return the success body
     */
    public static Success setSuccessBody(Object body) {
        Success success = new Success();
        success.setUid(DefaultValuesPopulator.getUid());
        success.setTimestamp(DefaultValuesPopulator.getCurrentTimestamp());
        success.setBody(body);
        return success;
    }

    /**
     * Read specific property string.
     *
     * @param body the body
     * @param prop the prop
     * @return the string
     */
    public static String readSpecificProperty(String body, String prop) {
        return JsonPath.read(body, prop);
    }
}

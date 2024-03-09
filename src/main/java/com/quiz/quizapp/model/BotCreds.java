package com.quiz.quizapp.model;

import com.google.auth.oauth2.GoogleCredentials;
import com.quiz.quizapp.exception.InternalServerErrorException;
import java.io.ByteArrayInputStream;

public final class BotCreds {

    private BotCreds() {
        throw new UnsupportedOperationException("Not Supported");
    }

    public static GoogleCredentials getCredentials() {
        try {
            String jsonContent = System.getenv("DIALOGFLOW_JSON_CONTENT");
            return GoogleCredentials.fromStream(new ByteArrayInputStream(jsonContent.getBytes()));
        } catch (Exception e) {
            throw new InternalServerErrorException("Issue fetching credentials for bot: " + e.getMessage());
        }
    }
}

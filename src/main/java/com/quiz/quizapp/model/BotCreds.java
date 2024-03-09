package com.quiz.quizapp.model;

import com.google.auth.oauth2.GoogleCredentials;
import com.quiz.quizapp.exception.InternalServerErrorException;

import java.io.FileInputStream;

public final class BotCreds {

    private BotCreds() {
        throw new UnsupportedOperationException("Not Supported");
    }

    public static GoogleCredentials getCredentials() {
        try (FileInputStream credentialsStream = new FileInputStream(System.getenv("DIALOGFLOW_JSON_FILE"))) {
            return GoogleCredentials.fromStream(credentialsStream);
        } catch (Exception e) {
            throw new InternalServerErrorException("Issue fetching credentials for bot: " + e.getMessage());
        }
    }
}

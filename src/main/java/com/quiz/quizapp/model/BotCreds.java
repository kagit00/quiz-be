package com.quiz.quizapp.model;

import com.google.auth.oauth2.GoogleCredentials;
import com.quiz.quizapp.dao.BotDao;
import com.quiz.quizapp.exception.InternalServerErrorException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public final class BotCreds {

    private BotCreds() {
        throw new UnsupportedOperationException("Not Supported");
    }


    public static GoogleCredentials getCredentials(BotDao botDao) {
        try {
            Optional<BotDetails> botDetailsOptional = botDao.findById(1);
            if (botDetailsOptional.isPresent()) {
                BotDetails botDetails = botDetailsOptional.get();
                String botDetailsAsString = botDetails.getBotDetailsAsJson();
                ByteArrayInputStream credentialsStream = new ByteArrayInputStream(botDetailsAsString.getBytes(StandardCharsets.UTF_8));
                return GoogleCredentials.fromStream(credentialsStream);
            }
        } catch (IOException e) {
            throw new InternalServerErrorException("Issue fetching credentials for bot: " + e.getMessage());
        }
        return null;
    }
}

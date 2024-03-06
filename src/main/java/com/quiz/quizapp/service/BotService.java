package com.quiz.quizapp.service;

import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.Struct;
import com.quiz.quizapp.exception.InternalServerErrorException;
import com.quiz.quizapp.model.BotCreds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BotService {

    @Value("${dialogflow.projectId}")
    private String projectId;

    public String handleBotQuery(String userQuery) {
        try (SessionsClient sessionsClient = SessionsClient.create(SessionsSettings.newBuilder()
                .setCredentialsProvider(BotCreds::getCredentials)
                .build())) {

            String sessionId = "unique-session-id";
            TextInput.Builder textInput = TextInput.newBuilder().setText(userQuery).setLanguageCode("en-US");
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            DetectIntentRequest detectIntentRequest =
                    DetectIntentRequest.newBuilder()
                            .setSession("projects/" + projectId + "/agent/sessions/" + sessionId)
                            .setQueryInput(queryInput)
                            .build();

            DetectIntentResponse detectIntentResponse = sessionsClient.detectIntent(detectIntentRequest);
            QueryResult queryResult = detectIntentResponse.getQueryResult();
            Struct parameters = queryResult.getParameters();
            com.google.protobuf.Value response = parameters.getFieldsOrThrow("response");
            return response.getStringValue();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}

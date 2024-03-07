package com.quiz.quizapp.controllers;

import com.quiz.quizapp.model.Success;
import com.quiz.quizapp.model.UserQuery;
import com.quiz.quizapp.service.BotService;
import com.quiz.quizapp.util.BasicUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
public class BotController {

    private final BotService botService;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping("/query")
    public ResponseEntity<Success> handleChatBotQuery(@RequestBody UserQuery userQuery) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(this.botService.handleBotQuery(userQuery.getQuery())), HttpStatus.OK);

    }
}

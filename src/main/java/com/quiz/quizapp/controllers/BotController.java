package com.quiz.quizapp.controllers;

import com.quiz.quizapp.service.BotService;
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
    public String handleChatBotQuery(@RequestBody String userQuery) {
        return this.botService.handleBotQuery(userQuery);
    }
}

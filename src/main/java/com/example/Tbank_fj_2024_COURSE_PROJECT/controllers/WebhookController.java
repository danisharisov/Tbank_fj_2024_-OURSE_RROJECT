package com.example.Tbank_fj_2024_COURSE_PROJECT.controllers;

import com.example.Tbank_fj_2024_COURSE_PROJECT.MovieBot;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebhookController {

    private final MovieBot movieBot;

    public WebhookController(MovieBot movieBot) {
        this.movieBot = movieBot;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> onUpdateReceived(@RequestBody Update update) {
        movieBot.onWebhookUpdateReceived(update);
        return ResponseEntity.ok("Webhook received");
    }
}
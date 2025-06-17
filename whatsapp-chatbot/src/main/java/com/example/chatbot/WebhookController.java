package com.example.chatbot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {

    // Endpoint for Facebook Webhook Verification
    @GetMapping("/")
    public ResponseEntity<String> verifyWebhook(
            @RequestParam(name = "hub.mode") String mode,
            @RequestParam(name = "hub.verify_token") String token,
            @RequestParam(name = "hub.challenge") String challenge) {

        // ✅ Set the same token here and in the Meta Developer Portal
        String VERIFY_TOKEN = "srav123token";

        if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.status(403).body("Verification failed");
        }
    }

    // Endpoint to receive messages from WhatsApp
    @PostMapping("/")
    public ResponseEntity<String> receiveWebhook(@RequestBody String payload) {
        System.out.println("📨 Incoming Webhook Payload: " + payload);
        return ResponseEntity.ok("EVENT_RECEIVED");
    }
}

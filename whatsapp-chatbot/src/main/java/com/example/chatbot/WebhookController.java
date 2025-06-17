package com.example.chatbot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {

    private static final String VERIFY_TOKEN = "srav123token";

    // GET endpoint for webhook verification (used by Facebook/WhatsApp when you register)
    @GetMapping("/webhook")
    public ResponseEntity<String> verifyWebhook(
            @RequestParam(name = "hub.mode", required = false) String mode,
            @RequestParam(name = "hub.verify_token", required = false) String token,
            @RequestParam(name = "hub.challenge", required = false) String challenge) {

        if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
            System.out.println("Webhook verified successfully.");
            return ResponseEntity.ok(challenge);
        } else {
            System.out.println("Webhook verification failed.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verification token mismatch");
        }
    }

    // POST endpoint to receive webhook messages from WhatsApp
    @PostMapping("/webhook")
    public ResponseEntity<Void> receiveWebhook(@RequestBody String payload) {
        System.out.println("Received webhook payload: " + payload);
        // You can add logic to parse the JSON payload and respond
        return ResponseEntity.ok().build();
    }
}

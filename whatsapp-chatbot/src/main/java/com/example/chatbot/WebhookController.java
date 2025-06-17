package com.example.chatbot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final String VERIFY_TOKEN = "srav123token"; // 🟢 must match Meta

    // Meta verification
    @GetMapping
    public ResponseEntity<String> verifyWebhook(
            @RequestParam(name = "hub.mode") String mode,
            @RequestParam(name = "hub.verify_token") String token,
            @RequestParam(name = "hub.challenge") String challenge) {

        if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
            return ResponseEntity.ok(challenge); // ✅ verified
        } else {
            return ResponseEntity.status(403).body("Verification failed");
        }
    }

    // Receiving actual webhook events
    @PostMapping
    public ResponseEntity<String> receiveWebhook(@RequestBody String payload) {
        System.out.println("Received webhook payload: " + payload);
        return ResponseEntity.ok("EVENT_RECEIVED");
    }
}

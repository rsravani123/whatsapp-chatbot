package com.example.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class WhatsAppChatbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhatsAppChatbotApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "✅ WhatsApp Chatbot is running!";
    }

    @PostMapping("/webhook")
    public String webhook(@RequestBody String payload) {
        System.out.println("Received webhook: " + payload);
        return "Webhook received!";
    }
}

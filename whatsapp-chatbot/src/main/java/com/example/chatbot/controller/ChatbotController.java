package com.example.chatbot.controller;

import com.example.chatbot.service.WhatsAppService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatbotController {

    private final WhatsAppService whatsAppService;

    public ChatbotController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String to, @RequestParam String message) {
        return whatsAppService.sendMessage(to, message);
    }
}

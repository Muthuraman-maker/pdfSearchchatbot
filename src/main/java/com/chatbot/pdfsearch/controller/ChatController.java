package com.chatbot.pdfsearch.controller;

import com.chatbot.pdfsearch.model.ChatRequest;
import com.chatbot.pdfsearch.model.ChatResponse;
import com.chatbot.pdfsearch.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatResponse ask(
            @RequestBody ChatRequest request) {

        return chatService.askQuestion(
                request.getDocumentId(),
                request.getQuestion(),
                request.getSearchMode());
    }
}
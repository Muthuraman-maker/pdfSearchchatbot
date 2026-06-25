package com.chatbot.pdfsearch.controller;

import com.chatbot.pdfsearch.service.InternetSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TavilyTestController {

    private final InternetSearchService internetSearchService;

    @GetMapping("/internet")
    public String search(
            @RequestParam String question) {

        return internetSearchService.search(
                question);
    }
}
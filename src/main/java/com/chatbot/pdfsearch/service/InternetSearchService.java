package com.chatbot.pdfsearch.service;

import com.chatbot.pdfsearch.model.TavilyRequest;
import com.chatbot.pdfsearch.model.TavilyResponse;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class InternetSearchService {

    private final RestClient restClient;

    @Value("${app.tavily.api-key}")
    private String apiKey;

    public String search(String question) {

        System.out.println(
                "Searching Internet for : "
                        + question);

        TavilyRequest request =
                TavilyRequest.builder()
                        .query(question)
                        .search_depth("basic")
                        .include_answer(true)
                        .max_results(5)
                        .build();

        TavilyResponse response =
                restClient.post()
                        .uri("https://api.tavily.com/search")
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .body(request)
                        .header(
                                "Authorization",
                                "Bearer " + apiKey)
                        .retrieve()
                        .body(TavilyResponse.class);

        return response.getAnswer();
    }
}

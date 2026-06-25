package com.chatbot.pdfsearch.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TavilyRequest {
    private String query;

    private String search_depth;

    private boolean include_answer;

    private int max_results;
}

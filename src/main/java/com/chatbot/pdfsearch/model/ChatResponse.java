package com.chatbot.pdfsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ChatResponse {

    private String answer;

    private String searchMode;

    private boolean pdfContextUsed;

    private boolean internetContextUsed;

    private int pdfChunksRetrieved;
}
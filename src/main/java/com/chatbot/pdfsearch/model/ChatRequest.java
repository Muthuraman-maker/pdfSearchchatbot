package com.chatbot.pdfsearch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
@Getter
@Setter
public class ChatRequest {

    private String documentId;
    private String question;
    private SearchMode searchMode;
}
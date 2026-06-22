package com.chatbot.pdfsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UploadResponse {

    private String documentId;

    private String message;
}
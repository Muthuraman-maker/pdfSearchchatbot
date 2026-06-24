package com.chatbot.pdfsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PdfSearchResult {

    private String context;

    private int chunkCount;
}
package com.chatbot.pdfsearch.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DocumentResponse {
    private String documentId;

    private String fileName;

    private Integer chunkCount;

    private LocalDateTime uploadedAt;
}

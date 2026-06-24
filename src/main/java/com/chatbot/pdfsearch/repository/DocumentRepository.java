package com.chatbot.pdfsearch.repository;

import com.chatbot.pdfsearch.model.DocumentMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository
        extends JpaRepository<DocumentMetadata, String> {
}
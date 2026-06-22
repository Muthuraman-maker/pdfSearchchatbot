package com.chatbot.pdfsearch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final VectorStoreService vectorStoreService;

    private final ChatClient.Builder chatClientBuilder;

    public String askQuestion(
            String documentId,
            String question) {

        List<Document> documents =
                vectorStoreService.search(
                        question);

        String context =
                documents.stream()
                        .map(Document::getText)
                        .collect(Collectors.joining("\n"));

        String prompt = """
                Answer the question using
                the context below.

                Context:
                %s

                Question:
                %s
                """
                .formatted(
                        context,
                        question);
        System.out.println("Question = " + question);

        System.out.println("Documents Found = "
                + documents.size());

        documents.forEach(doc ->
                System.out.println(
                        doc.getText()));
        return chatClientBuilder
                .build()
                .prompt()
                .user(prompt)
                .call()
                .content();
    }
}
package com.chatbot.pdfsearch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VectorStoreService {

    private final VectorStore vectorStore;

    public void save(List<Document> docs) {
        vectorStore.add(docs);
    }

    public List<Document> search(String documentId, String question) {

        SearchRequest request =
                SearchRequest.builder()
                        .query(question)
                        .topK(5)
                        .filterExpression("documentId =='"+documentId+"'")
                        .build();

        return vectorStore.similaritySearch(request);
    }
}
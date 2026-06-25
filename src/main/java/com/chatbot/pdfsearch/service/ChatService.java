package com.chatbot.pdfsearch.service;

import com.chatbot.pdfsearch.model.ChatResponse;
import com.chatbot.pdfsearch.model.PdfSearchResult;
import com.chatbot.pdfsearch.model.SearchMode;
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

    private final InternetSearchService internetSearchService;

    public ChatResponse askQuestion(
            String documentId,
            String question,
            SearchMode searchMode) {

        String pdfContext = "";
        String internetContext = "";

        boolean pdfContextUsed = false;
        boolean internetContextUsed = false;

        int pdfChunksRetrieved = 0;
        if (searchMode == SearchMode.PDF_ONLY) {
            PdfSearchResult result =
                    getPdfContext(
                            documentId,
                            question);

            pdfContext = result.getContext();
            pdfChunksRetrieved = result.getChunkCount();
            pdfContextUsed = true;
        }
        if (searchMode == SearchMode.INTERNET_ONLY) {

            internetContext =
                    internetSearchService.search(
                            question);
            internetContextUsed = true;
        }
        if (searchMode == SearchMode.PDF_AND_INTERNET) {

            PdfSearchResult result =
                    getPdfContext(
                            documentId,
                            question);

            pdfContext =
                    result.getContext();

            pdfChunksRetrieved =
                    result.getChunkCount();

            internetContext =
                    internetSearchService.search(
                            question);

            pdfContextUsed = true;
            internetContextUsed = true;
        }

        String prompt = """
        You are a helpful assistant.

        PDF Context:
        %s

        Internet Context:
        %s

        Question:
        %s

        Rules:

        1. Prefer PDF context only. Do not add any information. If answer exists,quote the exact content. otherwise say content not present in pdf.
        2. Use internet context only as supplement.
        3. If both contain similar information, generate a new definition combining both context.
        """
                .formatted(
                        pdfContext,
                        internetContext,
                        question);
        String answer =
                chatClientBuilder
                        .build()
                        .prompt()
                        .user(prompt)
                        .call()
                        .content();
        return new ChatResponse(
                answer,
                searchMode.name(),
                pdfContextUsed,
                internetContextUsed,
                pdfChunksRetrieved);
    }

    private PdfSearchResult getPdfContext(
            String documentId,
            String question) {

        List<Document> documents =
                vectorStoreService.search(
                        documentId,
                        question);

        String context =
                documents.stream()
                        .map(Document::getText)
                        .collect(Collectors.joining("\n"));

        return new PdfSearchResult(
                context,
                documents.size());
    }
}
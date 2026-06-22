package com.chatbot.pdfsearch.service;

import com.chatbot.pdfsearch.model.UploadResponse;
import com.chatbot.pdfsearch.processor.PdfProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final PdfProcessor pdfProcessor;
    private final VectorStoreService vectorStoreService;

    public UploadResponse upload(
            MultipartFile file) throws Exception {

        String documentId =
                UUID.randomUUID().toString();

        Path temp =
                Files.createTempFile(
                        "pdf-upload",
                        ".pdf");

        Files.write(
                temp,
                file.getBytes());

        List<Document> documents =
                pdfProcessor.extract(temp);

        TokenTextSplitter splitter =
                new TokenTextSplitter();

        List<Document> chunks =
                splitter.split(documents);

        chunks.forEach(doc ->
                doc.getMetadata()
                        .put(
                                "documentId",
                                documentId));

        vectorStoreService.save(chunks);

        return new UploadResponse(
                documentId,
                "PDF Uploaded Successfully");
    }
}
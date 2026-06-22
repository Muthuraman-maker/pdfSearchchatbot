package com.chatbot.pdfsearch.controller;

import com.chatbot.pdfsearch.model.UploadResponse;
import com.chatbot.pdfsearch.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> upload(
            @RequestParam("file")
            MultipartFile file) throws Exception {
        System.out.println("******** Controller Hit ********");
        System.out.println(file.getOriginalFilename());

        return ResponseEntity.ok(
                documentService.upload(file));
    }
}
package com.chatbot.pdfsearch.processor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
@Service
public class PdfProcessor {

    public List<Document> extract(Path path) {

        TikaDocumentReader reader =
                new TikaDocumentReader(
                        new FileSystemResource(path));

        return reader.get();
    }
}
# Project Progress Documentation

## Phase 1 - PDF RAG Foundation

### Objective

Build a chatbot capable of answering questions from uploaded PDF documents using Retrieval Augmented Generation (RAG).

---

## Feature 1: PDF Upload API

### Files Created

```text
DocumentController
DocumentService
UploadResponse
```

### Endpoint

```http
POST /api/documents/upload
```

### Purpose

Allows users to upload PDF files into the system.

### Flow

```text
User Uploads PDF
        ↓
DocumentController
        ↓
DocumentService
        ↓
PDF Processing
```

---

## Feature 2: PDF Text Extraction

### Files Created

```text
PdfProcessor
```

### Technologies Used

```text
Spring AI
TikaDocumentReader
```

### Purpose

Extracts readable text from uploaded PDF documents.

### Flow

```text
PDF
 ↓
TikaDocumentReader
 ↓
Spring AI Document Objects
```

---

## Feature 3: Chunking

### Files Modified

```text
DocumentService
```

### Technology Used

```java
TokenTextSplitter
```

### Purpose

Large PDFs cannot be directly converted into embeddings efficiently.

Therefore the PDF content is divided into smaller chunks.

### Example

Before:

```text
100 Page PDF
```

After:

```text
Chunk 1
Chunk 2
Chunk 3
Chunk 4
...
```

### Benefits

* Better semantic search
* Better retrieval accuracy
* Lower token usage

---

## Feature 4: OpenAI Embeddings

### Files Created

```text
VectorStoreService
```

### Technology Used

```text
text-embedding-3-small
```

### Purpose

Convert text chunks into numerical vectors.

### Example

```text
Spring Boot
```

becomes:

```text
[0.123, 0.456, 0.789 ...]
```

These vectors represent the semantic meaning of the content.

---

## Feature 5: PGVector Integration

### Technologies Used

```text
PostgreSQL
PGVector
Docker
```

### Docker Container

```bash
docker run -d \
--name pgvector \
-p 5432:5432 \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgress \
pgvector/pgvector:pg16
```

### Purpose

Store embeddings generated from PDF chunks.

### Table

```text
vector_store
```

Stores:

```text
Chunk Content
Embedding Vector
Metadata
```

---

## Feature 6: Metadata Storage

### Metadata Added

```json
{
  "documentId": "UUID"
}
```

### Purpose

Every chunk is linked to its original document.

### Example

```text
Document A
    ├── Chunk 1
    ├── Chunk 2
    ├── Chunk 3
```

All chunks contain:

```json
{
  "documentId":"123"
}
```

---

## Feature 7: Question Answering API

### Files Created

```text
ChatController
ChatService
ChatRequest
ChatResponse
```

### Endpoint

```http
POST /api/chat
```

### Purpose

Allows users to ask questions against uploaded documents.

### Flow

```text
Question
   ↓
Similarity Search
   ↓
Retrieve Relevant Chunks
   ↓
OpenAI
   ↓
Answer
```

---

## Feature 8: Semantic Search

### Files Modified

```text
VectorStoreService
```

### Purpose

Searches vectors instead of keywords.

### Example

PDF:

```text
Dependency Injection
```

User Query:

```text
Inversion of Control
```

Traditional Search:

```text
No Match
```

Semantic Search:

```text
Relevant Match Found
```

---

# Phase 2 - Advanced Search Modes

### Objective

Allow users to search:

```text
PDF Only
Internet Only
PDF + Internet
```

---

## Feature 9: SearchMode Enum

### Files Created

```text
SearchMode
```

### Values

```java
PDF_ONLY

INTERNET_ONLY

PDF_AND_INTERNET
```

### Purpose

Control how answers are generated.

---

## Feature 10: Enhanced ChatRequest

### Files Modified

```text
ChatRequest
```

Added:

```java
SearchMode searchMode;
```

### Example Request

```json
{
  "documentId":"123",
  "question":"What is Spring Boot?",
  "searchMode":"PDF_AND_INTERNET"
}
```

---

## Feature 11: Tavily Integration

### Files Created

```text
InternetSearchService
TavilyRequest
TavilyResponse
RestClientConfig
```

### Technology Used

```text
Tavily Search API
RestClient
```

### Purpose

Retrieve live internet information.

### Flow

```text
Question
    ↓
Tavily API
    ↓
Internet Results
```

---

## Feature 12: INTERNET_ONLY Mode

### Flow

```text
Question
    ↓
Tavily Search
    ↓
OpenAI
    ↓
Answer
```

No vector database search is performed.

---

## Feature 13: PDF_AND_INTERNET Mode

### Flow

```text
Question
      ↓
  ┌───────────────┐
  │               │
PDF Search    Internet Search
  │               │
  └───────┬───────┘
          ↓
     Combined Prompt
          ↓
       OpenAI
          ↓
       Answer
```

### Priority Rule

```text
PDF Content
     >
Internet Content
```

PDF remains the source of truth.

---

# Phase 3 - Response Enrichment

### Objective

Provide transparency to users regarding how answers were generated.

---

## Feature 14: ChatResponse Enhancement

### Files Modified

```text
ChatResponse
```

### New Fields

```java
private String answer;

private String searchMode;

private boolean pdfContextUsed;

private boolean internetContextUsed;

private int pdfChunksRetrieved;
```

---

## Feature 15: Source Tracking

### Purpose

Expose which sources contributed to the answer.

### Example

```json
{
  "pdfContextUsed": true,
  "internetContextUsed": false
}
```

Meaning:

```text
Answer generated entirely from PDF content.
```

---

## Feature 16: Chunk Retrieval Metrics

### Files Modified

```text
ChatService
PdfSearchResult
```

### Purpose

Track how many chunks were retrieved from the vector database.

### Example

```json
{
  "pdfChunksRetrieved": 5
}
```

Meaning:

```text
5 document chunks were used
to generate the answer.
```

---

# Current Architecture

```text
User
 │
 ▼
Angular UI (Future)
 │
 ▼
Spring Boot
 │
 ├── Document Upload
 │
 ├── Chat API
 │
 ├── Tavily Internet Search
 │
 └── OpenAI
 │
 ▼
PGVector
 │
 ▼
PostgreSQL
```

---

# Upcoming Features

## Phase 4

### Document Management

```text
GET /api/documents

DELETE /api/documents/{documentId}
```

### Purpose

Manage uploaded documents.

---

## Phase 5

### Angular Frontend

Features:

```text
PDF Upload
Document Selection
Search Mode Selection
Chat Interface
Source Display
```

---

## Phase 6

### Future Enhancements

```text
Conversation Memory

Chat History

Streaming Responses

User Authentication

S3 Storage

Docker Compose

Kubernetes Deployment
```

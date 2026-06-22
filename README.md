PDF Search Chatbot using Spring AI, OpenAI and PGVectorOverview

This project is a Retrieval Augmented Generation (RAG) based chatbot built using:

Java 17
Spring Boot 3.5.x
Spring AI
OpenAI
PostgreSQL
PGVector
Apache Tika

The application allows users to:

Upload PDF documents.
Extract content from PDFs.
Convert PDF content into vector embeddings.
Store embeddings in PostgreSQL using PGVector.
Ask questions about uploaded PDFs.
Retrieve relevant content using semantic search.
Generate AI-powered answers using OpenAI.

What Problem Are We Solving?

Suppose a PDF contains 100 pages of Spring Boot interview questions.

A user asks:

What is Dependency Injection?

Without RAG:

Question -> OpenAI -> Generic answer from model knowledge

The model does not know anything about our uploaded PDF.

With RAG:

Question -> Search uploaded PDF -> Retrieve relevant content -> Send context to OpenAI -> Generate answer

Now the answer is generated using our document content.

What is RAG?

RAG stands for:

Retrieval + Augmented + Generation

Retrieval

Find relevant content from uploaded documents.

Example:

PDF Content:

Spring Boot
Kafka
Hibernate
Microservices

Question:

What is Kafka?

Retrieve:

Kafka related chunks

Augmentation

Inject retrieved content into the prompt.

Example:

Context:
Kafka is a distributed streaming platform...

Question:
What is Kafka?
Generation

OpenAI generates the final answer using the retrieved context.

Why Not Send Entire PDF to OpenAI?

Sending a large PDF on every request is:

Slow
Expensive
Token intensive

Instead:

PDF -> Retrieve Top 5 Relevant Chunks -> Send Only Relevant Content -> Generate Answer

This is the primary benefit of RAG.

What is an Embedding?

Computers cannot understand text directly.

Example:

Spring Boot

Embeddings convert text into vectors.

Example:

[0.123,
0.456,
0.789,
0.234,
...]

These vectors represent semantic meaning.

Why Are Embeddings Important?

Suppose the PDF contains:

Dependency Injection is a design pattern...

User asks:

Explain Inversion of Control

Traditional keyword search may fail.

Embedding search understands semantic similarity.

This is called:

Semantic Search

What is a Vector Database?

Traditional relational databases store:

id
name
salary

Vector databases store:

Text + Embedding Vector

Example:

Chunk:
Spring Boot is an open source framework...

Embedding:
[0.123, 0.567, 0.890...]

This enables semantic similarity search.

Why PGVector?

PGVector extends PostgreSQL with vector search capabilities.

Benefits:

Free
Open Source
Production Ready
PostgreSQL Based
Spring AI Integration
Easy Deployment

High Level Architecture
Document Upload Flow
User
│
│ Upload PDF
▼
DocumentController
│
▼
DocumentService
│
▼
PdfProcessor
│
▼
Chunking
│
▼
OpenAI Embedding Model
│
▼
PGVector Database
*********************************************
Question Answer Flow

User Question
│
▼
ChatController
│
▼
ChatService
│
▼
Similarity Search
│
▼
PGVector
│
▼
Relevant Chunks
│
▼
OpenAI Chat Model
│
▼
Answer


Project Structure

src/main/java

com.chatbot.pdfsearch

├── controller
│   ├── DocumentController
│   └── ChatController
│
├── service
│   ├── DocumentService
│   ├── ChatService
│   └── VectorStoreService
│
├── rag
│   └── PdfProcessor
│
├── dto
│   ├── ChatRequest
│   ├── ChatResponse
│   └── UploadResponse
│
└── config

OpenAI Models Used

Embedding Model
text-embedding-3-small

Purpose:

Generate embeddings for vector search

Chat Model
gpt-4.1-mini

Purpose:

Generate final answers

Learning Summary

This project demonstrates the complete lifecycle of a modern AI-powered RAG application:

PDF
↓
Text Extraction
↓
Chunking
↓
Embedding Generation
↓
Vector Storage
↓
Semantic Retrieval
↓
Prompt Augmentation
↓
LLM Answer Generation

This architecture is widely used in enterprise AI assistants, document search platforms, internal knowledge bots, and customer support chatbots.
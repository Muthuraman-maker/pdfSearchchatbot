**Why We Need Document Filtering**

Currently:

PDF A
PDF B
PDF C

all chunks live in the same:

vector_store table.

When user asks:

{
"documentId":"PDF-A",
"question":"What is Spring Boot?"
}

our current code does:

vectorStore.similaritySearch(...)

which searches:

PDF A
PDF B
PDF C

together.

**Goal**

Search only:

documentId = PDF-A
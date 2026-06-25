select count(*) from vector_store;
select * from vector_store;
select metadata from vector_store limit 5;

select left(content,300)
from vector_store
limit 3;

select metadata
from vector_store
limit 5;

select * from documents;

CREATE TABLE documents (
    document_id VARCHAR(100) PRIMARY KEY,
    file_name VARCHAR(500) NOT NULL,
    chunk_count INTEGER NOT NULL,
    uploaded_at TIMESTAMP NOT NULL
);
package com.nice.ex.documents.service;

import com.nice.ex.documents.entity.Document;

import java.util.Optional;

public interface IDocumentService {

    Document addDocument(Document document);

    Optional<Document> findDocumentById(int id);

    Iterable<Document> findAllDocuments();

}

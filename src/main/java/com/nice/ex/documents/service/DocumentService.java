package com.nice.ex.documents.service;

import com.nice.ex.documents.entity.Document;
import com.nice.ex.documents.repository.DocumentRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Retryable( value = HibernateException.class, exclude = DataIntegrityViolationException.class, maxAttemptsExpression = "${retry.maxAttempts}",
        backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
public class DocumentService implements IDocumentService {

    @Autowired DocumentRepository documentRepository;

    @Override
    public Document addDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Optional<Document> findDocumentById(int id) {
        return documentRepository.findById(id);
    }

    @Override
    public Iterable<Document> findAllDocuments() {
        return documentRepository.findAll();
    }
}

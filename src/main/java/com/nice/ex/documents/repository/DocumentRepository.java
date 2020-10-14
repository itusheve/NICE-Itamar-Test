package com.nice.ex.documents.repository;

import com.nice.ex.documents.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document,Integer> {
}

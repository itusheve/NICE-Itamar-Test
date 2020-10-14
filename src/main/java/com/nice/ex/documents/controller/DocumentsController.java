package com.nice.ex.documents.controller;

import com.nice.ex.documents.exceptionHandler.APIError;
import com.nice.ex.documents.entity.Document;
import com.nice.ex.documents.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class DocumentsController {

    @Autowired IDocumentService documentService;


    @PostMapping("/add")
    public ResponseEntity<Document> addDocument(@RequestBody @Valid Document document){
        Document savedDocument = documentService.addDocument(document);
        return ResponseEntity.ok(savedDocument);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getDocument(@PathVariable int id){
        Optional<Document> documentOptional = documentService.findDocumentById(id);
        if(documentOptional.isPresent()){
            return ResponseEntity.ok(documentOptional.get());
        } else {
            return ResponseEntity.badRequest().body(new APIError("Document with id " + id + " not found."));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Document>> getAll() {
        return ResponseEntity.ok(documentService.findAllDocuments());
    }

}

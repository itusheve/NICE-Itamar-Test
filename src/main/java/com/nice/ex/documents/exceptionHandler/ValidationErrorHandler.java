package com.nice.ex.documents.exceptionHandler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.HashMap;
import java.util.Map;

import static com.nice.ex.documents.entity.Document.NAME_UNIQUE_CONSTRAINT_NAME;


@ControllerAdvice
public class ValidationErrorHandler extends ResponseEntityExceptionHandler {

    private static final String CONSTRAINT_PREFIX = "document.";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> result = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMsg = error.getDefaultMessage();
            result.put(fieldName,errorMsg);
        });

        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(value
            = { DataIntegrityViolationException.class })
    protected ResponseEntity<Object> handleUniqueConstraint(
            DataIntegrityViolationException ex, WebRequest request) {
        ConstraintViolationException violationEx = (ConstraintViolationException) ex.getCause();
        UniqueConstraintError errorMessage = buildErrorMessage(violationEx.getConstraintName());
        return ResponseEntity.badRequest().body(errorMessage);
    }

    private UniqueConstraintError buildErrorMessage(String constraintName) {

        switch(constraintName){
            case (CONSTRAINT_PREFIX + NAME_UNIQUE_CONSTRAINT_NAME):
                return new UniqueConstraintError("name");
        }

        return new UniqueConstraintError();
    }

}

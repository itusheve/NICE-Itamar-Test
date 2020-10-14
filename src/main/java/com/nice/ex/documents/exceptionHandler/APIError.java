package com.nice.ex.documents.exceptionHandler;

public class APIError {

    private String errorMessage;

    public APIError(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

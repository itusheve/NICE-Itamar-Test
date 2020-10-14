package com.nice.ex.documents.exceptionHandler;

public class UniqueConstraintError extends APIError {

    private String fieldName;

    public UniqueConstraintError(){
        super("Unknown constraint violation.");
    }

    public UniqueConstraintError(String fieldName){
        super(fieldName + " must be unique.");
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

}

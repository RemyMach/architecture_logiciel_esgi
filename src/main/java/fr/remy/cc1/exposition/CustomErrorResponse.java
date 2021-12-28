package fr.remy.cc1.exposition;

import fr.remy.cc1.exposition.exception.ExpositionExceptionsDictionary;

public class CustomErrorResponse{
    public String errorCode;
    public String message;

    public CustomErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}

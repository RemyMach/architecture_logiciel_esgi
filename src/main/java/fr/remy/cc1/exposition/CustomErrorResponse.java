package fr.remy.cc1.exposition;

import org.springframework.http.HttpStatus;

public class CustomErrorResponse{
    public int errorCode;
    public String message;

    public CustomErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}

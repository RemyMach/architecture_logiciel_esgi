package fr.remy.cc1.exposition;

import org.springframework.http.HttpStatus;

public class ResponseError {
    private HttpStatus httpStatus;
    private int errorCode;
    private String message;

    public ResponseError(HttpStatus httpStatus, int errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}

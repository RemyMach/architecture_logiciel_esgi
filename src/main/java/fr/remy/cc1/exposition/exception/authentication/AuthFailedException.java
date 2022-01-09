package fr.remy.cc1.exposition.exception.authentication;

import fr.remy.cc1.kernel.error.BasicException;

import java.io.IOException;

public class AuthFailedException extends IOException implements BasicException {

    private final String errorCode;

    public AuthFailedException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}

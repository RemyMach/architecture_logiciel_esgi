package fr.remy.cc1.shared.exposition.exception.authentication;

import fr.remy.cc1.kernel.error.BasicException;

import java.io.IOException;

public class AuthFailedException extends IOException implements BasicException {

    private final String errorCode;

    public AuthFailedException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}

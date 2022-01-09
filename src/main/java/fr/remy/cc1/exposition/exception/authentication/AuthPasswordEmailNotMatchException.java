package fr.remy.cc1.exposition.exception.authentication;

import fr.remy.cc1.kernel.error.BasicException;

public class AuthPasswordEmailNotMatchException extends Exception implements BasicException {

    private final String errorCode;

    public AuthPasswordEmailNotMatchException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}

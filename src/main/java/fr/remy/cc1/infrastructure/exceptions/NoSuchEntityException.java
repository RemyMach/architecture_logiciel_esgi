package fr.remy.cc1.infrastructure.exceptions;

import fr.remy.cc1.kernel.error.BasicException;

public class NoSuchEntityException extends Exception implements BasicException {
    private String errorCode;

    public NoSuchEntityException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }
}

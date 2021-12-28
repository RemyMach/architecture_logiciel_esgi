package fr.remy.cc1.kernel.error;

public class ValidationException extends Exception implements BasicException{
    private String errorCode;

    public ValidationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }
}

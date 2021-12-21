package fr.remy.cc1.kernel.error;

public class ValidationException extends Exception implements BasicException{
    private int errorCode;

    public ValidationException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }
}

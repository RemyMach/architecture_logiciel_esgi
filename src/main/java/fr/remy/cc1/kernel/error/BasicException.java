package fr.remy.cc1.kernel.error;

public class BasicException extends Exception {

    private final int errorCode;

    public BasicException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

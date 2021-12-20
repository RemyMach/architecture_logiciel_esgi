package fr.remy.cc1.kernel.error;

public class BasicError extends Error {

    public final int errorCode;

    protected BasicError(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}

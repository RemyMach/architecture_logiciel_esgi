package fr.remy.cc1.kernel.error;

public class DurationValidationException extends ValidationException {
    public DurationValidationException(String errorCode, String message) {
        super(errorCode, message);
    }
}

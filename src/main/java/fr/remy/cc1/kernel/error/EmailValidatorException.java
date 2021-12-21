package fr.remy.cc1.kernel.error;

public class EmailValidatorException extends ValidationException {
    public EmailValidatorException(int errorCode, String message) {
        super(errorCode, message);
    }
}

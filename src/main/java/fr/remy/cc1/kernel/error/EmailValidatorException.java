package fr.remy.cc1.kernel.error;

public class EmailValidatorException extends BasicException {
    public EmailValidatorException(int errorCode, String message) {
        super(errorCode, message);
    }
}

package fr.remy.cc1.kernel.error;

public class PasswordValidatorException extends ValidationException {
    public PasswordValidatorException(int errorCode, String message) {
        super(errorCode, message);
    }
}

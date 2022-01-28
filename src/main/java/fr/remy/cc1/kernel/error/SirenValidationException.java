package fr.remy.cc1.kernel.error;

public class SirenValidationException extends ValidationException{
    public SirenValidationException(String errorCode, String message) {
        super(errorCode, message);
    }
}

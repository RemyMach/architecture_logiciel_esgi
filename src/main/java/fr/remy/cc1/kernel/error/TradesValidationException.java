package fr.remy.cc1.kernel.error;

public class TradesValidationException extends ValidationException{
    public TradesValidationException(String errorCode, String message) {
        super(errorCode, message);
    }
}

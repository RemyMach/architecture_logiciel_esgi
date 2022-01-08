package fr.remy.cc1.kernel.error;

public class CurrencyValidationException extends ValidationException {
    public CurrencyValidationException(String errorCode, String message) {
        super(errorCode, message);
    }
}

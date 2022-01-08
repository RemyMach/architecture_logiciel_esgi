package fr.remy.cc1.kernel.error;

public class PaymentProcessValidationException extends ValidationException {
    public PaymentProcessValidationException(String errorCode, String message) {
        super(errorCode, message);
    }
}

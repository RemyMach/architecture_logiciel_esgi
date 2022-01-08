package fr.remy.cc1.kernel.error;

public class PaymentMethodValidationException extends ValidationException {
    public PaymentMethodValidationException(String errorCode, String message) {
        super(errorCode, message);
    }
}

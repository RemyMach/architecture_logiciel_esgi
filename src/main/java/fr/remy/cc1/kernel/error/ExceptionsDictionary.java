package fr.remy.cc1.kernel.error;

public class ExceptionsDictionary {
    public static final ValidationException EMAIL_NOT_VALID = new ValidationException(1, "the email is not valid");
    public static final ValidationException PASSWORD_NOT_VALID = new ValidationException(2, "the password is not valid");

    public static final ValidationException CURRENCY_NOT_PRESENT = new ValidationException(3, "the currency is not supported by the application");

    public static final ValidationException PAYMENT_NOT_PRESENT = new ValidationException(4, "the payment method is not supported by the application");
}

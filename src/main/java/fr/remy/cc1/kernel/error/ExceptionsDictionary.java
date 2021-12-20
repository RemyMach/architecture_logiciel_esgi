package fr.remy.cc1.kernel.error;

public class ExceptionsDictionary {

    public static final BasicException EMAIL_NOT_VALID = new BasicException(1, "the email is not valid");
    public static final BasicException PASSWORD_NOT_VALID = new BasicException(2, "the password is not valid");

    public static final BasicException CURRENCY_NOT_PRESENT = new BasicException(3, "the currency is not supported by the application");

    public static final BasicException PAYMENT_NOT_PRESENT = new BasicException(4, "the payment method is not supported by the application");

    public static final BasicException FIELD_IS_NULL = new BasicException(5, "the field don't have to be null");
}

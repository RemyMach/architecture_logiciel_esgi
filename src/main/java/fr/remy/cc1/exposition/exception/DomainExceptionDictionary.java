package fr.remy.cc1.exposition.exception;

import fr.remy.cc1.kernel.error.ValidationException;

import java.util.Map;

public class DomainExceptionDictionary {

    public static final Map<String, String> codeToExpositionErrors = Map.ofEntries(
            Map.entry("email_not_valid", "the email is not valid"),
            Map.entry("password_not_valid", "the password is not valid"),
            Map.entry("currency_not_supported", "the currency is not supported by the application"),
            Map.entry("payment_method_not_supported", "the payment method is not supported by the application"),
            Map.entry("field_null", "the field don't have to be null")
    );
    public static final ValidationException EMAIL_NOT_VALID = new ValidationException(1, "the email is not valid");
    public static final ValidationException PASSWORD_NOT_VALID = new ValidationException(2, "the password is not valid");

    public static final ValidationException CURRENCY_NOT_PRESENT = new ValidationException(3, "the currency is not supported by the application");

    public static final ValidationException PAYMENT_NOT_PRESENT = new ValidationException(4, "the payment method is not supported by the application");

    public static final ValidationException USER_CATEGORY_NOT_VALID = new ValidationException(5, "the userCategory is not valid");
}

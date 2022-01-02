package fr.remy.cc1.exposition.exception;

import java.util.Map;

public class DomainExceptionsDictionary {

    public static final Map<String, String> codeToExpositionErrors = Map.ofEntries(
            Map.entry("email_not_valid", "the email is not valid"),
            Map.entry("password_not_valid", "the password is not valid"),
            Map.entry("currency_not_supported", "the currency is not supported by the application"),
            Map.entry("payment_method_not_supported", "the payment method is not supported by the application"),
            Map.entry("user_category_not_valid", "the userCategory is not valid")
    );
}
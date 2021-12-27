package fr.remy.cc1.exposition.exception;

import java.util.Map;

public class ExceptionsDictionary {

    public static final Map<Integer, String> codeToExpositionErrors = Map.ofEntries(
            Map.entry(0, "System error"),
            Map.entry(1, "the email is not valid"),
            Map.entry(2, "the password is not valid"),
            Map.entry(3, "the currency is not supported by the application"),
            Map.entry(4, "the payment method is not supported by the application"),
            Map.entry(5, "the field don't have to be null"),
            Map.entry(6, "field don't have to be null"),
            Map.entry(7, "password field don't have to be null or blank"),
            Map.entry(8, "firstname field don't have to be null or blank"),
            Map.entry( 9, "lastname field don't have to be null or blank"),
            Map.entry(10, "Email field don't have to be null or blank"),
            Map.entry(11, "UserCategory field don't have to be null or blank")
    );
}

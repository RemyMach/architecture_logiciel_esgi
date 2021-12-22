package fr.remy.cc1.exposition.exception;

import java.util.Map;

public class ExceptionsDictionary {

    public static final Map<Integer, String> codeToExpositionErrors = Map.of(
            0, "System error",
            1, "the email is not valid",
            2, "the password is not valid",
            3, "the currency is not supported by the application",
            4, "the payment method is not supported by the application",
            5, "the field don't have to be null",
            6, "field don't have to be null",
            7, "password field don't have to be null or blank",
            8, "firstname field don't have to be null or blank",
            9, "lastname field don't have to be null or blank",
            10, "Email field don't have to be null or blank",
            11, "UserCategory field don't have to be null or blank",
    );
}

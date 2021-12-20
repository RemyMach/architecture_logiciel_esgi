package fr.remy.cc1.kernel.error;

import fr.remy.cc1.domain.payment.CurrencyValidator;

public class ErrorsDictionary {

    public final BasicError EMAIL_NOT_VALID = new BasicError(1, "the email is not valid");
    public final BasicError PASSWORD_NOT_VALID = new BasicError(2, "the password is not valid");

    public final BasicError CURRENCY_NOT_PRESENT = new BasicError(3, "the currency is not supported by the application");
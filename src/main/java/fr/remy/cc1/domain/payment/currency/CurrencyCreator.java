package fr.remy.cc1.domain.payment.currency;

import fr.remy.cc1.kernel.error.CurrencyValidationException;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;

public class CurrencyCreator {

    public CurrencyCreator() {}

    public static Currency getValueOf(String currencyCode) throws CurrencyValidationException {

        if(!CurrencyValidator.getInstance().test(currencyCode)) {
            throw new CurrencyValidationException(ExceptionsDictionary.CURRENCY_NOT_PRESENT.getErrorCode(), ExceptionsDictionary.CURRENCY_NOT_PRESENT.getMessage());
        }

        return Currency.valueOf(currencyCode);
    }
}

package fr.remy.cc1.domain;

import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;
import fr.remy.cc1.infrastructure.payment.PaypalPayment;

public class CurrencyBuild {

    private String exceptionMessage;

    public CurrencyBuild() {
        this.exceptionMessage = "You can choose uniquely";
        for (Currency currency: Currency.values()) {
            this.exceptionMessage += ", " + currency.name();
        }

        this.exceptionMessage += " to pay";
    }

    public Currency getCurrencyOf(String currencyCode) {
        if(!CurrencyValidator.getInstance().test(currencyCode)) {
            throw new IllegalArgumentException(exceptionMessage);
        }

        return Currency.valueOf(currencyCode);
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}

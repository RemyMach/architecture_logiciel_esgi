package fr.remy.cc1.domain.payment;

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
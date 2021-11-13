package fr.remy.cc1.domain.payment.creditcard;

public class CreditCard {

    private final CreditCardId creditCardId;

    private final int number;

    private final int expiryDate;

    private final int securityCode;

    private final String name;

    private CreditCard(CreditCardId creditCardId, int number, int expiryDate, int securityCode, String name) {
        this.creditCardId = creditCardId;
        this.number = number;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
        this.name = name;
    }

    public static CreditCard of(CreditCardId creditCardId, int number, int expiryDate, int securityCode, String name) {
        return new CreditCard(creditCardId, number, expiryDate, securityCode, name);
    }

    public CreditCardId getCreditCardId() {
        return creditCardId;
    }

    public int getSecurityCode() {
        return securityCode;
    }
}

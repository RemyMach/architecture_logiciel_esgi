package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.user.UserId;

public class CreditCard {

    private final CreditCardId creditCardId;

    private final String number;

    private final int expiryDate;

    private final int securityCode;

    private final String name;

    private final UserId userId;

    private CreditCard(CreditCardId creditCardId, String number, int expiryDate, int securityCode, String name, UserId userId) {
        this.creditCardId = creditCardId;
        this.number = number;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
        this.name = name;
        this.userId = userId;
    }

    public static CreditCard of(CreditCardId creditCardId, String number, int expiryDate, int securityCode, String name,  UserId userId) {
        return new CreditCard(creditCardId, number, expiryDate, securityCode, name, userId);
    }

    public CreditCardId getCreditCardId() {
        return creditCardId;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public UserId getUserId() {
        return userId;
    }
}

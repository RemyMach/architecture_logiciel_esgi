package fr.remy.cc1.subscription.domain.creditcard;

import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.subscription.domain.Card;

import java.util.Objects;

public final class CreditCard implements Card {

    private final CreditCardId creditCardId;

    private final String number;

    private final int expiryDate;

    private final int securityCode;

    private final String name;

    //TODO gardé ça dans le InMemory
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
    @Override
    public CreditCardId getCreditCardId() {
        return creditCardId;
    }

    @Override
    public int getSecurityCode() {
        return securityCode;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public int getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String getName() {
        return name;
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return expiryDate == that.expiryDate && securityCode == that.securityCode && Objects.equals(creditCardId, that.creditCardId) && Objects.equals(number, that.number) && Objects.equals(name, that.name) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardId, number, expiryDate, securityCode, name, userId);
    }
}

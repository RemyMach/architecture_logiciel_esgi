package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.payment.creditcard.CreditCardId;

public interface Card {
    public CreditCardId getCreditCardId();

    public int getSecurityCode();

    public String getNumber();

    public int getExpiryDate();

    public String getName();
}

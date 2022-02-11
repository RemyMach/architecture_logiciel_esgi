package fr.remy.cc1.subscription.domain;

import fr.remy.cc1.subscription.domain.creditcard.CreditCardId;

public interface Card {
    public CreditCardId getCreditCardId();

    public int getSecurityCode();

    public String getNumber();

    public int getExpiryDate();

    public String getName();
}

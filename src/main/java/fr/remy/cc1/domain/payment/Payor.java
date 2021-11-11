package fr.remy.cc1.domain.payment;

public class Payor {

    private final CreditCard creditCard;

    private final PaypalAccount paypalAccount;

    public Payor(CreditCard creditCard, PaypalAccount paypalAccount) {
        this.creditCard = creditCard;
        this.paypalAccount = paypalAccount;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public PaypalAccount getPaypalAccount() {
        return paypalAccount;
    }
}

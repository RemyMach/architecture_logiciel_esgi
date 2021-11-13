package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;

import java.util.Arrays;
import java.util.List;

public class Payer {

    public static final List<String> PAYMENT_METHOD_SUPPORTED = Arrays.asList("Paypal", "CreditCard");

    private final CreditCard creditCard;

    private final PaypalAccount paypalAccount;

    public Payer(CreditCard creditCard, PaypalAccount paypalAccount) {
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

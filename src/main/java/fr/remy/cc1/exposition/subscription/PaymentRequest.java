package fr.remy.cc1.exposition.subscription;

import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.exposition.subscription.CardRequest;
import fr.remy.cc1.exposition.subscription.PaypalRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PaymentRequest {

    public String currency;

    public String name;

    public CardRequest card;

    public PaypalRequest paypal;

    public CardRequest getCard() {
        return card;
    }

    public void setCard(CardRequest card) {
        this.card = card;
    }

    public PaypalRequest getPaypal() {
        return paypal;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaypal(PaypalRequest paypal) {
        this.paypal = paypal;
    }
}

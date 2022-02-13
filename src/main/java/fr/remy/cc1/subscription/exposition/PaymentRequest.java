package fr.remy.cc1.subscription.exposition;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PaymentRequest {

    @NotNull(message = "payment_currency_empty_null")
    public String currency;

    @NotNull(message = "payment_name_empty_null")
    public String name;

    @NotNull(message = "card_parameters_empty_or_null")
    @Valid
    public CardRequest card;

    @NotNull(message = "paypal_parameters_empty_or_null")
    @Valid
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

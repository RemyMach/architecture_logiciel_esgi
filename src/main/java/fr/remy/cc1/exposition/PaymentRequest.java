package fr.remy.cc1.exposition;

import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.exposition.subscription.CardRequest;
import fr.remy.cc1.exposition.subscription.PaypalRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentRequest {

    @NotBlank(message = "16")
    @NotNull(message = "16")
    public String currency;

    @NotBlank(message = "17")
    @NotNull(message = "17")
    public String name;

    public CardRequest cardRequest;

    public PaypalRequest paypalRequest;
}

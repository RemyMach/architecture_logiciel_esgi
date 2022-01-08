package fr.remy.cc1.exposition.subscription;

import fr.remy.cc1.domain.payment.paypal.PaypalAccount;

import javax.validation.constraints.NotNull;

public class PaypalRequest {
    @NotNull(message="userId_empty_null")
    public PaypalAccount paypalAccount;
}

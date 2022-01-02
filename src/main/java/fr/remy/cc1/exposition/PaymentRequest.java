package fr.remy.cc1.exposition;

import fr.remy.cc1.domain.payment.paypal.PaypalAccount;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaymentRequest {

    @NotBlank(message = "16")
    @NotNull(message = "16")
    public String currency;

    @NotBlank(message = "17")
    @NotNull(message = "17")
    public String name;

    public boolean saveCreditCard;

    public String creditCardNumber;

    public Integer creditCardExpiryDate;

    public Integer creditCardSecurityCode;

    public String creditCardName;

    public PaypalAccount paypalAccount;
}

package fr.remy.cc1.exposition.subscription;

import javax.validation.constraints.NotNull;

public class PaypalRequest {

    @NotNull(message = "paypal_parameters_empty_or_null")
    public String paypalAccountName;

    public String getPaypalAccountName() {
        return paypalAccountName;
    }

    public void setPaypalAccountName(String paypalAccountName) {
        this.paypalAccountName = paypalAccountName;
    }
}

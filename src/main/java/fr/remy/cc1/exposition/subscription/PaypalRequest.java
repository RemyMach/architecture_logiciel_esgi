package fr.remy.cc1.exposition.subscription;

import fr.remy.cc1.domain.payment.paypal.PaypalAccount;

import javax.validation.constraints.NotEmpty;

public class PaypalRequest {

    public String paypalAccountName;

    public String getPaypalAccountName() {
        return paypalAccountName;
    }

    public void setPaypalAccountName(String paypalAccountName) {
        this.paypalAccountName = paypalAccountName;
    }
}

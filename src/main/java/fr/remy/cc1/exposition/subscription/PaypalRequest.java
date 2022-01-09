package fr.remy.cc1.exposition.subscription;

import fr.remy.cc1.domain.payment.paypal.PaypalAccount;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

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

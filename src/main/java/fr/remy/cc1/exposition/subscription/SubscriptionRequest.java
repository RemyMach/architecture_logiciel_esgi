package fr.remy.cc1.exposition.subscription;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

// Getter and setter mandatory for Spring validation
public class SubscriptionRequest {

    @NotNull(message="discountPercentage_empty_null")
    public Integer discountPercentage;

    @NotNull(message="amount_empty_null")
    public BigDecimal amount;

    @NotNull(message="userId_empty_null")
    public Integer userId;

    @NotNull(message="payment_empty_null")
    @Valid
    public PaymentRequest payment;

    public PaymentRequest getPayment() {
        return payment;
    }

    public void setPayment(PaymentRequest payment) {
        this.payment = payment;
    }
}

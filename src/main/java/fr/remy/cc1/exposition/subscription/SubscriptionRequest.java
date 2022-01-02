package fr.remy.cc1.exposition.subscription;

import fr.remy.cc1.exposition.PaymentRequest;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class SubscriptionRequest {

    @NotNull(message="discountPercentage_empty_null")
    public Integer discountPercentage;

    @NotNull(message="amount_empty_null")
    public BigDecimal amount;

    @NotNull(message="userId_empty_null")
    public Integer userId;

    @NotNull(message="payment_empty_null")
    public PaymentRequest payment;
}

package fr.remy.cc1.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class SubscriptionRequest {

    @NotBlank(message="discountPercentage_empty_null")
    @NotNull(message="discountPercentage_empty_null")
    public int discountPercentage;

    @NotBlank(message="amount_empty_null")
    @NotNull(message="amount_empty_null")
    public BigDecimal amount;

    @NotNull(message="userId_empty_null")
    @NotBlank(message="userId_empty_null")
    public int userId;

    @NotBlank(message="payment_empty_null")
    @NotNull(message="payment_empty_null")
    public PaymentRequest payment;
}

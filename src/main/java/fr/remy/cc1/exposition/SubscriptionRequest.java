package fr.remy.cc1.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class SubscriptionRequest {

    @NotBlank(message="13")
    @NotNull(message="13")
    public int discountPercentage;

    @NotBlank(message="12")
    @NotNull(message="12")
    public BigDecimal amount;

    @NotNull(message="15")
    @NotBlank(message="15")
    public int userId;

    @NotBlank(message="14")
    @NotNull(message="14")
    public PaymentRequest payment;
}

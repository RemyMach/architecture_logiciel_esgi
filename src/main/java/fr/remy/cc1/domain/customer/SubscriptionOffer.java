package fr.remy.cc1.domain.customer;

import java.math.BigDecimal;

public class SubscriptionOffer {

    private final BigDecimal amount;

    private final int discountPercentage;

    private SubscriptionOffer(BigDecimal amount, int discountPercentage) {
        this.amount = amount;
        this.discountPercentage = discountPercentage;
    }

    public static SubscriptionOffer of(BigDecimal amount, int discountPercentage) {
       return new SubscriptionOffer(amount, discountPercentage);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }
}

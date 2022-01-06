package fr.remy.cc1.domain.customer;

import fr.remy.cc1.domain.payment.Money;

public class SubscriptionOffer {

    private final Money money;

    private final int discountPercentage;

    private SubscriptionOffer(Money money, int discountPercentage) {
        this.money = money;
        this.discountPercentage = discountPercentage;
    }

    public static SubscriptionOffer of(Money money, int discountPercentage) {
       return new SubscriptionOffer(money, discountPercentage);
    }

    public Money getMoney() {
        return money;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }
}

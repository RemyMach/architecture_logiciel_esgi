package fr.remy.cc1.subscription.domain.customer;

import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.domain.money.Money;

public final class SubscriptionOffer {

    private final SubscriptionOfferId subscriptionOfferId;

    private final Money money;

    private final int discountPercentage;

    private final UserId userId;

    private SubscriptionOffer(Money money, int discountPercentage, SubscriptionOfferId subscriptionOfferId, UserId userId) {
        this.money = money;
        this.discountPercentage = discountPercentage;
        this.subscriptionOfferId = subscriptionOfferId;
        this.userId = userId;
    }

    public static SubscriptionOffer of(Money money, int discountPercentage, SubscriptionOfferId subscriptionOfferId, UserId userId) {
       return new SubscriptionOffer(money, discountPercentage, subscriptionOfferId, userId);
    }

    public Money getMoney() {
        return money;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public SubscriptionOfferId getSubscriptionOfferId() {
        return subscriptionOfferId;
    }
}

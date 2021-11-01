package fr.remy.cc1.domain;

public class SubscriptionOffer {

    private final float price;
    private final Currency currency;
    private final int discountPercentage;


    private SubscriptionOffer(float price, Currency currency, int discountPercentage) {
        this.price = price;
        this.currency = currency;
        this.discountPercentage = discountPercentage;
    }

    public static SubscriptionOffer of(float price, Currency currency, int discountPercentage) {
       return new SubscriptionOffer(price, currency, discountPercentage);
    }
}

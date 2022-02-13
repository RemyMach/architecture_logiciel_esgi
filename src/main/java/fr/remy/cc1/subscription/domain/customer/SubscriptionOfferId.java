package fr.remy.cc1.subscription.domain.customer;

import java.util.Objects;

public class SubscriptionOfferId {

    private final int value;

    private SubscriptionOfferId(int value) {
        this.value = value;
    }

    public static SubscriptionOfferId of(int value) {
        return new SubscriptionOfferId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionOfferId that = (SubscriptionOfferId) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

package fr.remy.cc1.infrastructure.InMemory;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;

import java.util.Map;

public class UserSubscriptionsData {

    private static volatile UserSubscriptionsData instance;

    public final Map<UserId, SubscriptionOffer> data;

    private UserSubscriptionsData(Map<UserId, SubscriptionOffer> data) {
        this.data = data;
    }

    public static UserSubscriptionsData getInstance() {

        synchronized(UserSubscriptionsData.class) {
            if (instance == null) {
                throw new Error("you have to use setUp function to use ContractorsData");
            }
            return instance;
        }
    }

    public static void setup(Map<UserId, SubscriptionOffer> data) {
        synchronized(UserSubscriptionsData.class) {
            instance = new UserSubscriptionsData(data);
        }
    }
}

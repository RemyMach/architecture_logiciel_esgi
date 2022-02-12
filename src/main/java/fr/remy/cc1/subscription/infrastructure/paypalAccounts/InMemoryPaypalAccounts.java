package fr.remy.cc1.subscription.infrastructure.paypalAccounts;

import fr.remy.cc1.subscription.domain.paypal.PayPalAccountId;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccount;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccounts;
import fr.remy.cc1.domain.UserId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPaypalAccounts implements PaypalAccounts {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<PayPalAccountId, PaypalAccount> data = new ConcurrentHashMap<>();

    @Override
    public void save(PaypalAccount paypalAccount) {
        data.put(paypalAccount.getPayPalAccountId(), paypalAccount);
    }

    @Override
    public PayPalAccountId nextIdentity() {
        return PayPalAccountId.of(counter.incrementAndGet());
    }

    @Override
    public PaypalAccount byId(PayPalAccountId payPalAccountId) {
        return data.get(payPalAccountId);
    }


    @Override
    public PaypalAccount findByUserId(UserId userId) {
        return data.values().stream().filter(entry -> entry.getUserId().equals(userId)).findFirst().orElseThrow();
    }

    @Override
    public void delete(PayPalAccountId payPalAccountId) {
        data.remove(payPalAccountId);
    }
}

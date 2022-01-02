package fr.remy.cc1.infrastructure.paypalAccounts;

import fr.remy.cc1.domain.payment.paypal.PayPalAccountId;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.domain.payment.paypal.PaypalAccounts;
import fr.remy.cc1.domain.user.UserId;

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
    public PaypalAccount byUserId(UserId userId) {
        return data.values().stream().filter(entry -> entry.getUserId().equals(userId)).findFirst().orElseThrow();
    }
}

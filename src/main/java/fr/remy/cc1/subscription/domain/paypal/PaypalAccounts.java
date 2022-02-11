package fr.remy.cc1.subscription.domain.paypal;
import fr.remy.cc1.member.domain.user.UserId;

public interface PaypalAccounts {
    void save(PaypalAccount paypalAccount);

    PayPalAccountId nextIdentity();

    PaypalAccount byId(PayPalAccountId PayPalAccountId);

    void delete(PayPalAccountId PayPalAccountId);

    PaypalAccount findByUserId(UserId userId);
}

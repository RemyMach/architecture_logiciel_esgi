package fr.remy.cc1.domain.payment.paypal;
import fr.remy.cc1.domain.user.UserId;

public interface PaypalAccounts {
    void save(PaypalAccount paypalAccount);

    PayPalAccountId nextIdentity();

    PaypalAccount byId(PayPalAccountId PayPalAccountId);

    PaypalAccount byUserId(UserId userId);
}

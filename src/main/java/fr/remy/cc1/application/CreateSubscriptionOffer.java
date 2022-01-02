package fr.remy.cc1.application;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.kernel.Command;

import java.math.BigDecimal;

public class CreateSubscriptionOffer implements Command {

    public final int discountPercentage;

    public final BigDecimal amount;

    public final int userId;

    public final String currency;

    public final String payment;

    public CreateSubscriptionOffer(int discountPercentage, BigDecimal amount, int userId, String currency, String payment) {
        this.discountPercentage = discountPercentage;
        this.amount = amount;
        this.userId = userId;
        this.currency = currency;
        this.payment = payment;
    }
}

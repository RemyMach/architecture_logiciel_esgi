package fr.remy.cc1.subscription.application;

import fr.remy.cc1.kernel.Command;
import fr.remy.cc1.shared.domain.UserId;

import java.math.BigDecimal;

public class CreateSubscriptionOffer implements Command {

    public final int discountPercentage;
    public final BigDecimal amount;
    public final UserId userId;
    public final String currency;
    public final String payment;

    public CreateSubscriptionOffer(int discountPercentage, BigDecimal amount, UserId userId, String currency, String payment) {
        this.discountPercentage = discountPercentage;
        this.amount = amount;
        this.userId = userId;
        this.currency = currency;
        this.payment = payment;
    }
}

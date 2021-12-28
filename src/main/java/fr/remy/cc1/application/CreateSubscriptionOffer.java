package fr.remy.cc1.application;

import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.kernel.Command;

import java.math.BigDecimal;

public class CreateSubscriptionOffer implements Command {

    public final String discountPercentage;

    public final BigDecimal amount;

    public final int userId;

    public CreateSubscriptionOffer(String discountPercentage, BigDecimal amount, int userId) {
        this.discountPercentage = discountPercentage;
        this.amount = amount;
        this.userId = userId;
    }
}

package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.user.User;

import java.math.BigDecimal;

public final class Invoice {

    private final BigDecimal amount;

    private final User user;


    private Invoice(BigDecimal amount, User user) {
        this.amount = amount;
        this.user = user;
    }

    public static Invoice of(BigDecimal amount, User user) {
        return new Invoice(amount, user);
    }
}

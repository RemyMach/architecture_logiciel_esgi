package fr.remy.cc1.shared.domain.money;

import fr.remy.cc1.kernel.error.CurrencyValidationException;
import fr.remy.cc1.subscription.domain.currency.Currency;

import java.math.BigDecimal;
import java.util.Objects;

public final class Money {
    public final BigDecimal amount;
    public final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = Objects.requireNonNull(amount);
        this.currency = Objects.requireNonNull(currency);
    }

    public static Money of(BigDecimal amount, Currency currency) throws CurrencyValidationException {
        return new Money(amount, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.equals(money.amount) &&
                currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}

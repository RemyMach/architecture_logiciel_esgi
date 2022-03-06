package fr.remy.cc1.subscription.domain;


import fr.remy.cc1.kernel.error.PaymentProcessValidationException;
import fr.remy.cc1.shared.domain.money.Money;

public interface Payment {

    void start(Money money) throws PaymentProcessValidationException;
}

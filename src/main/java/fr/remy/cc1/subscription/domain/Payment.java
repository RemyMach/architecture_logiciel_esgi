package fr.remy.cc1.subscription.domain;


import fr.remy.cc1.kernel.error.PaymentProcessValidationException;

public interface Payment {

    void start(Money money) throws PaymentProcessValidationException;
}

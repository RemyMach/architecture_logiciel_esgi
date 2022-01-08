package fr.remy.cc1.domain.payment;


import fr.remy.cc1.kernel.error.PaymentProcessValidationException;

public interface Payment {

    void start(Money money) throws PaymentProcessValidationException;
}

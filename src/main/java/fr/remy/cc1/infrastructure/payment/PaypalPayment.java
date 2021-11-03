package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.payment.Payment;

public class PaypalPayment implements Payment {
    @Override
    public boolean process(int amount) {
        return false;
    }
}

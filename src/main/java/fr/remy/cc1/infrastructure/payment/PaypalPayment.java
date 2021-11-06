package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.payment.Payment;

import java.math.BigDecimal;

public class PaypalPayment implements Payment {
    @Override
    public boolean start(BigDecimal amount) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}

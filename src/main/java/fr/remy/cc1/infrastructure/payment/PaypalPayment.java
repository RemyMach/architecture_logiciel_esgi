package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.payment.Payment;

import java.math.BigDecimal;

public class PaypalPayment implements Payment {
    @Override
    public boolean start(BigDecimal amount) {
        this.displayMessage();
        return false;
    }

    private void displayMessage() {
        System.out.println("je suis une pômme affiche toi");
    }
}

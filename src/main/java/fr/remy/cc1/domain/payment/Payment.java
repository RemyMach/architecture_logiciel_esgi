package fr.remy.cc1.domain.payment;

import java.math.BigDecimal;

public interface Payment {

    //TODO replace by Money
    void start(BigDecimal amount);
}

package fr.remy.cc1.domain.payment;

import java.math.BigDecimal;

public interface Payment {

    void start(Money money);
}

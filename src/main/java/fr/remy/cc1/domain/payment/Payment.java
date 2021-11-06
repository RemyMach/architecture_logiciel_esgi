package fr.remy.cc1.domain.payment;

import java.math.BigDecimal;

public interface Payment {

    boolean start(BigDecimal amount);
}

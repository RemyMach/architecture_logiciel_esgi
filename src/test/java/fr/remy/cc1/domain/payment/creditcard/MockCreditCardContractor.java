package fr.remy.cc1.domain.payment.creditcard;

import java.math.BigDecimal;

public class MockCreditCardContractor extends CreditCardContractor {

    private Integer countProcess = 0;

    public void process(CreditCard creditCard, BigDecimal amount) {
        this.countProcess += 1;
        super.checkNext(creditCard, amount);
    }

    public Integer getCountProcess() {
        return this.countProcess;
    }
}

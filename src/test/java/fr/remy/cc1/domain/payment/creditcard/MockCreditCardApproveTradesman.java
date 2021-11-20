package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.mock.CountProcess;

import java.math.BigDecimal;

public class MockCreditCardApproveTradesman extends CreditCardApproveTradesman implements CountProcess {

    private Integer countProcess = 0;

    public void process(CreditCard creditCard, BigDecimal amount) {
        this.countProcess += 1;
        super.checkNext(creditCard, amount);
    }

    @Override
    public Integer getCountProcess() {
        return this.countProcess;
    }
}

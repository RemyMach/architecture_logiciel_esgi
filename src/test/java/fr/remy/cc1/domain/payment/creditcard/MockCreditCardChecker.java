package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.mock.CountProcess;
import fr.remy.cc1.domain.payment.Money;

import java.math.BigDecimal;

public class MockCreditCardChecker extends CreditCardChecker implements CountProcess {

    private Integer countProcess = 0;

    public void process(CreditCard creditCard, Money money) {
        this.countProcess += 1;
        super.checkNext(creditCard, money);
    }

    @Override
    public Integer getCountProcess() {
        return this.countProcess;
    }
}

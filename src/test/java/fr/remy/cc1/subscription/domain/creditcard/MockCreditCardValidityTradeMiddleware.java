package fr.remy.cc1.subscription.domain.creditcard;

import fr.remy.cc1.shared.domain.mock.CountProcess;
import fr.remy.cc1.shared.domain.money.Money;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;

public class MockCreditCardValidityTradeMiddleware extends CreditCardValidityTradeMiddleware implements CountProcess {

    private Integer countProcess = 0;

    public void process(CreditCard creditCard, Money money) throws PaymentProcessValidationException {
        this.countProcess += 1;
        super.checkNext(creditCard, money);
    }

    @Override
    public Integer getCountProcess() {
        return this.countProcess;
    }
}

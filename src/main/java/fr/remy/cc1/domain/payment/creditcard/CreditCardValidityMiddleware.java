package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.payment.PaymentCardMiddleware;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;

public class CreditCardValidityMiddleware implements PaymentCardMiddleware {

    private PaymentCardMiddleware nextMiddleware;

    @Override
    public void process(CreditCard creditCard, Money money) throws PaymentProcessValidationException {

        if(creditCard.getName() == null || creditCard.getSecurityCode() == 0 || creditCard.getExpiryDate() == 0 || creditCard.getNumber() == null) {

            throw new PaymentProcessValidationException(ExceptionsDictionary.CARD_PAYMENT_VALIDATION_ERROR.getErrorCode(), ExceptionsDictionary.CARD_PAYMENT_VALIDATION_ERROR.getMessage());
        }
        if(creditCard.getSecurityCode() == 420) {
            throw new PaymentProcessValidationException(ExceptionsDictionary.CARD_PAYMENT_VALIDATION_ERROR.getErrorCode(), ExceptionsDictionary.CARD_PAYMENT_VALIDATION_ERROR.getMessage());
        }
        checkNext(creditCard, money);
    }

    @Override
    public void setNext(PaymentCardMiddleware middleware) {
        this.nextMiddleware = middleware;
    }

    @Override
    public void checkNext(CreditCard creditCard, Money money) throws PaymentProcessValidationException {
        if(this.nextMiddleware != null) {
            this.nextMiddleware.process(creditCard, money);
        }
    }
}

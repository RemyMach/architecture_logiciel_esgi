package fr.remy.cc1.domain.payment.PaymentMethod;

import fr.remy.cc1.domain.payment.currency.Currency;
import fr.remy.cc1.kernel.error.CurrencyValidationException;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.PaymentMethodValidationException;

public class PaymentMethodCreator {

    public static PaymentMethod getValueOf(String paymentMethod) throws PaymentMethodValidationException {
        if(!PaymentMethodValidator.test(paymentMethod)) {
            throw new PaymentMethodValidationException(ExceptionsDictionary.PAYMENT_NOT_PRESENT.getErrorCode(), ExceptionsDictionary.PAYMENT_NOT_PRESENT.getMessage());
        }

        return PaymentMethod.valueOf(paymentMethod);
    }
}

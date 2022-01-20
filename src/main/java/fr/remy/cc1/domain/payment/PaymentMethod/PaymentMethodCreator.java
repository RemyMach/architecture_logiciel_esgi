package fr.remy.cc1.domain.payment.PaymentMethod;

import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.PaymentMethodValidationException;

public final class PaymentMethodCreator {

    public static PaymentMethod getValueOf(String paymentMethod) throws PaymentMethodValidationException {
        if(!PaymentMethodValidator.getInstance().test(paymentMethod)) {
            throw new PaymentMethodValidationException(ExceptionsDictionary.PAYMENT_NOT_PRESENT.getErrorCode(), ExceptionsDictionary.PAYMENT_NOT_PRESENT.getMessage());
        }

        return PaymentMethod.valueOf(paymentMethod);
    }
}

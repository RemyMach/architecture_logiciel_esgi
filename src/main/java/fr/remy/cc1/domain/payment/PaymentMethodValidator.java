package fr.remy.cc1.domain.payment;

import java.util.function.Predicate;

public class PaymentMethodValidator implements Predicate<String> {

    private static final PaymentMethodValidator INSTANCE = new PaymentMethodValidator();

    private PaymentMethodValidator() { }

    public static PaymentMethodValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(String paymentMethod) {

        return Payer.PAYMENT_METHOD_SUPPORTED.contains(paymentMethod);
    }
}

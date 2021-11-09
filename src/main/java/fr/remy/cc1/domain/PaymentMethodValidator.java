package fr.remy.cc1.domain;

import fr.remy.cc1.domain.payment.PaymentBuild;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PaymentMethodValidator implements Predicate<String> {

    private static final PaymentMethodValidator INSTANCE = new PaymentMethodValidator();

    private PaymentMethodValidator() { }

    public static PaymentMethodValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(String paymentMethod) {

        return PaymentBuild.PAYMENT_METHOD_SUPPORTED.contains(paymentMethod);
    }
}

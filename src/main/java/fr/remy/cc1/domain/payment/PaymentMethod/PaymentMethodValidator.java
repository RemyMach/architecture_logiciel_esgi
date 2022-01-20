package fr.remy.cc1.domain.payment.PaymentMethod;

import fr.remy.cc1.kernel.EnumValidator;

import java.util.function.Predicate;

public final class PaymentMethodValidator implements Predicate<String> {

    private static final PaymentMethodValidator INSTANCE = new PaymentMethodValidator();

    private PaymentMethodValidator() { }

    public static PaymentMethodValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(String paymentMethod) {

        EnumValidator<PaymentMethod> enumValidator = new EnumValidator(PaymentMethod.class);
        return enumValidator.test(paymentMethod);
    }
}

package fr.remy.cc1.subscription.domain.currency;


import fr.remy.cc1.kernel.EnumValidator;

import java.util.function.Predicate;

public final class CurrencyValidator implements Predicate<String> {

    private static final CurrencyValidator INSTANCE = new CurrencyValidator();

    private CurrencyValidator() { }

    public static CurrencyValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(String currencyCode) {
        EnumValidator<Currency> enumValidator = new EnumValidator(Currency.class);
        return enumValidator.test(currencyCode);
    }

}

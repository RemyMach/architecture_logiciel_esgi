package fr.remy.cc1.domain.payment;

import java.util.function.Predicate;

public class CurrencyValidator implements Predicate<String> {

    private static final CurrencyValidator INSTANCE = new CurrencyValidator();

    private CurrencyValidator() { }

    public static CurrencyValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(String CurrencyCode) {
        try {
            Currency.valueOf(CurrencyCode);
            return true;
        }catch(IllegalArgumentException illegalArgumentException) {
            return false;
        }
    }
}

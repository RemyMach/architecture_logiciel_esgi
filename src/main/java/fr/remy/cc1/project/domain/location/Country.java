package fr.remy.cc1.project.domain.location;

import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.TradesValidationException;
import fr.remy.cc1.kernel.error.ValidationException;

import java.util.Arrays;

public enum Country {
    FRANCE("france"),
    EUROPE("europe"),
    SPAIN("spain");

    private final String code;

    Country(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Country getUnitFromCode(String code) throws ValidationException {
        return Arrays.stream(Country.values())
                .filter(country -> country.getCode().equals(code))
                .findFirst()
                .orElseThrow(
                        () -> new TradesValidationException(ExceptionsDictionary.TRADES_VALIDATION_ERROR.getErrorCode(), ExceptionsDictionary.TRADES_VALIDATION_ERROR.getMessage())
                );
    }
}

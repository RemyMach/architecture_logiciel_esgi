package fr.remy.cc1.legacy.domain.trades;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.TradesValidationException;
import fr.remy.cc1.kernel.error.ValidationException;

import java.util.Arrays;

public enum ETrade {
    LUMBERJACK("lumberjack"),
    CARPENTER("carpenter"),
    MASON("mason");

    private final String code;

    ETrade(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ETrade getUnitFromCode(String code) throws ValidationException {
        return Arrays.stream(ETrade.values())
            .filter(trade -> trade.getCode().equals(code))
            .findFirst()
            .orElseThrow(
                    () -> new TradesValidationException(ExceptionsDictionary.TRADES_VALIDATION_ERROR.getErrorCode(), ExceptionsDictionary.TRADES_VALIDATION_ERROR.getMessage())
            );
    }
}

package fr.remy.cc1.shared.domain.trade;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.TradesValidationException;
import fr.remy.cc1.kernel.error.ValidationException;

import java.util.Arrays;

public enum TradeJobs {
    LUMBERJACK("lumberjack"),
    CARPENTER("carpenter"),
    MASON("mason");

    private final String jobName;

    TradeJobs(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public static TradeJobs getTradeFromJobName(String jobName) throws ValidationException {
        return Arrays.stream(TradeJobs.values())
            .filter(trade -> trade.getJobName().equals(jobName))
            .findFirst()
            .orElseThrow(
                    () -> new TradesValidationException(ExceptionsDictionary.TRADES_VALIDATION_ERROR.getErrorCode(), ExceptionsDictionary.TRADES_VALIDATION_ERROR.getMessage())
            );
    }
}

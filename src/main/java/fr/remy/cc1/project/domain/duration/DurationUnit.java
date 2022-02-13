package fr.remy.cc1.project.domain.duration;

import fr.remy.cc1.kernel.error.DurationValidationException;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ValidationException;

public enum DurationUnit {
    HOUR("h"),
    DAY("d"),
    WEEK("w"),
    MONTH("m"),
    YEAR("y");

    private final String code;

    DurationUnit(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static DurationUnit getUnitFromCode(String code) throws ValidationException {
        DurationUnit durationUnit = null;
        for (DurationUnit unit : DurationUnit.values()) {
            if (unit.getCode().equals(code)) {
                durationUnit = unit;
            }
        }

        if (durationUnit == null) {
            throw new DurationValidationException(ExceptionsDictionary.UNRECOGNIZED_DURATION_UNIT.getErrorCode(), ExceptionsDictionary.UNRECOGNIZED_DURATION_UNIT.getMessage());
        }

        return durationUnit;
    }

    @Override
    public String toString() {
        return "DurationUnit{" +
                "code='" + code + '\'' +
                '}';
    }
}

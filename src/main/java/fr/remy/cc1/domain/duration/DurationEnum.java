package fr.remy.cc1.domain.duration;

public enum DurationEnum {
    HOUR("h"),
    DAY("d"),
    WEEK("w"),
    MONTH("m"),
    YEAR("y");

    private final String code;

    DurationEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "DurationEnum{" +
                "code='" + code + '\'' +
                '}';
    }
}

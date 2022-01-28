package fr.remy.cc1.domain.user;

import java.util.Objects;

public class TradesmanId {

    private final int value;

    private TradesmanId(int value) {
        this.value = value;
    }

    public static TradesmanId of(int value) {
        return new TradesmanId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradesmanId that = (TradesmanId) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

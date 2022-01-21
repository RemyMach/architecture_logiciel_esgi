package fr.remy.cc1.domain.duration;

import java.util.Objects;

public final class Duration {

    private final int length;
    private final DurationUnit unit;

    private Duration(int length, DurationUnit unit) {
        this.length = length;
        this.unit = unit;
    }

    public static Duration of(int length, DurationUnit unit) {
        return new Duration(length, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duration duration = (Duration) o;
        return length == duration.length && unit == duration.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, unit);
    }

    @Override
    public String toString() {
        return "Duration{" +
                "length=" + length +
                ", unit=" + unit +
                '}';
    }
}

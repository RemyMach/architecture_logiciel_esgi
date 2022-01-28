package fr.remy.cc1.domain.user;

import java.util.Objects;

public class ContractorId {

    private final int value;

    private ContractorId(int value) {
        this.value = value;
    }

    public static ContractorId of(int value) {
        return new ContractorId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractorId that = (ContractorId) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

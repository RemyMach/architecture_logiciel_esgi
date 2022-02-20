package fr.remy.cc1.projectTradesmen.domain;

import fr.remy.cc1.kernel.ValueObjectId;

import java.util.Objects;

public final class ProjectTradesmenId implements ValueObjectId {
    private final int value;

    private ProjectTradesmenId(int value) {
        this.value = value;
    }

    public static ProjectTradesmenId of(int value) {
        return new ProjectTradesmenId(value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTradesmenId)) return false;
        ProjectTradesmenId that = (ProjectTradesmenId) o;
        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "ProjectTradesmenId{" +
                "value=" + value +
                '}';
    }
}

package fr.remy.cc1.domain.project;

import fr.remy.cc1.kernel.ValueObjectId;

import java.util.Objects;

public final class ProjectId implements ValueObjectId {

    private final int value;

    private ProjectId(int value) {
        this.value = value;
    }

    public static ProjectId of(int value) {
        return new ProjectId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectId projectId = (ProjectId) o;
        return value == projectId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

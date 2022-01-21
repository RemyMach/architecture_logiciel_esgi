package fr.remy.cc1.domain.skill;

import java.util.Objects;

public final class Skill {

    private final String name;

    private Skill(String name) {
        this.name = name;
    }

    public static Skill of(String name) {
        return new Skill(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                '}';
    }
}

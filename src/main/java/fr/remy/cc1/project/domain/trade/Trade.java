package fr.remy.cc1.project.domain.trade;

import java.util.Objects;

public final class Trade {
    private final String name;

    private Trade(String name) {
        this.name = name;
    }

    public static Trade of(String name) {
        return new Trade(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Objects.equals(name, trade.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "name='" + name + '\'' +
                '}';
    }
}

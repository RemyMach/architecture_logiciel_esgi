package fr.remy.cc1.domain.trades;

import java.util.Objects;

public final class Trades {
    private final String name;

    private Trades(String name) {
        this.name = name;
    }

    public static Trades of(String name) {
        return new Trades(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trades trades = (Trades) o;
        return Objects.equals(name, trades.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Trades{" +
                "name='" + name + '\'' +
                '}';
    }
}

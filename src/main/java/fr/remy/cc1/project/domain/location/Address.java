package fr.remy.cc1.project.domain.location;

import java.util.Objects;

public final class Address {

    private final String address;

    private Address(String address) {
        this.address = address;
    }

    public static Address of(String address) {
        return new Address(address);
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                '}';
    }
}

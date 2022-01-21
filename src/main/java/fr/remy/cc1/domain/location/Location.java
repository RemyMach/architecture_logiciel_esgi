package fr.remy.cc1.domain.location;

import java.util.Objects;

public final class Location {

    private final Address address;
    private final LatLng latLng;

    private Location(Address address, LatLng latLng) {
        this.address = address;
        this.latLng = latLng;
    }

    public static Location of(Address address, LatLng latLng) {
        return new Location(address, latLng);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(address, location.address) && Objects.equals(latLng, location.latLng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, latLng);
    }

    @Override
    public String toString() {
        return "Location{" +
                "address=" + address +
                ", latLng=" + latLng +
                '}';
    }
}

package fr.remy.cc1.project.domain.location;

import java.math.BigDecimal;
import java.util.Objects;

public final class LatLng {

    private final BigDecimal latitude;
    private final BigDecimal longitude;

    private LatLng(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static LatLng of(BigDecimal latitude, BigDecimal longitude) {
        return new LatLng(latitude, longitude);
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LatLng latLng = (LatLng) o;
        return Objects.equals(latitude, latLng.latitude) && Objects.equals(longitude, latLng.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "LatLng{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

package fr.remy.cc1.domain.location;

public interface LocationGeocoding {
    LatLng processAdresse(Address address);
}

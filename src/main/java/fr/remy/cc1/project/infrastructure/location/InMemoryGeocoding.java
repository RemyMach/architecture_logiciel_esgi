package fr.remy.cc1.project.infrastructure.location;

import fr.remy.cc1.project.domain.location.Address;
import fr.remy.cc1.project.domain.location.LatLng;
import fr.remy.cc1.project.domain.location.LocationGeocoding;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.LocationValidationException;
import fr.remy.cc1.kernel.error.ValidationException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryGeocoding implements LocationGeocoding {

    private final Map<String, LatLng> addresses = new ConcurrentHashMap<>();

    public InMemoryGeocoding() {

    }

    @Override
    public LatLng processAddress(Address address) throws ValidationException {
        LatLng latLng = addresses.get(address.getAddress());
        if (latLng == null) {
            throw new LocationValidationException(ExceptionsDictionary.GEOCODING_ERROR.getErrorCode(), ExceptionsDictionary.GEOCODING_ERROR.getMessage());
        }
        return latLng;
    }

    public void addLocation(String locationName, LatLng latLng) {
        this.addresses.put(locationName, latLng);
    }
}

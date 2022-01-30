package fr.remy.cc1.domain.location;

import fr.remy.cc1.kernel.error.ValidationException;

public interface LocationGeocoding {
    LatLng processAddress(Address address) throws ValidationException;
}

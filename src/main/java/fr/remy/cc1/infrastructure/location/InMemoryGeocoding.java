package fr.remy.cc1.infrastructure.location;

import fr.remy.cc1.domain.location.Address;
import fr.remy.cc1.domain.location.LatLng;
import fr.remy.cc1.domain.location.LocationGeocoding;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryGeocoding implements LocationGeocoding {

    private final Map<String, LatLng> addresses = new ConcurrentHashMap<>();

    public InMemoryGeocoding() {
        this.addresses.put("Paris", LatLng.of(BigDecimal.valueOf(48.864716), BigDecimal.valueOf(2.349014)));
        this.addresses.put("Toulon", LatLng.of(BigDecimal.valueOf(43.125832), BigDecimal.valueOf(5.930556)));
        this.addresses.put("Lille", LatLng.of(BigDecimal.valueOf(50.629250), BigDecimal.valueOf(3.057256)));
        this.addresses.put("Lyon", LatLng.of(BigDecimal.valueOf(45.763420), BigDecimal.valueOf(4.834277)));
        this.addresses.put("Grenoble", LatLng.of(BigDecimal.valueOf(45.171547), BigDecimal.valueOf(5.722387)));
        this.addresses.put("Reims", LatLng.of(BigDecimal.valueOf(49.262798), BigDecimal.valueOf(4.034700)));
        this.addresses.put("Nantes", LatLng.of(BigDecimal.valueOf(47.218102), BigDecimal.valueOf(-1.552800)));
        this.addresses.put("Toulouse", LatLng.of(BigDecimal.valueOf(43.604500), BigDecimal.valueOf(1.444000)));
        this.addresses.put("Aix-en-Provence", LatLng.of(BigDecimal.valueOf(43.526302), BigDecimal.valueOf(5.445429)));
    }

    @Override
    public LatLng processAdresse(Address address) {
        LatLng latLng = addresses.get(address.getAddress());
        return null;
    }
}

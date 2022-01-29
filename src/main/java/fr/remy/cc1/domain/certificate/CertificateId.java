package fr.remy.cc1.domain.certificate;

import java.util.Objects;

public class CertificateId {

    private final int value;

    private CertificateId(int value) {
        this.value = value;
    }

    public static CertificateId of(int value) {
        return new CertificateId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateId that = (CertificateId) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

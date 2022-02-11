package fr.remy.cc1.member.domain.company;

import fr.remy.cc1.kernel.error.ValidationException;

import java.util.Objects;

public class Company {

    public final Siren siren;

    public final String name;

    private Company(Siren siren, String name) {
        this.siren = siren;
        this.name = name;
    }

    public static Company of(String sirenCandidate, String name) throws ValidationException {
        Siren siren = Siren.of(sirenCandidate);
        return new Company(siren, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(siren, company.siren) &&
                Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siren, name);
    }
}

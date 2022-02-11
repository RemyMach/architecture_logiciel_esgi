package fr.remy.cc1.member.domain.company;

import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.SirenValidationException;
import fr.remy.cc1.kernel.error.ValidationException;

import java.util.Objects;

public class Siren {

    public final String number;

    private Siren(String number) {
        this.number = number;
    }

    public static Siren of(String sirenCandidate) throws ValidationException {
        if(!SirenValidator.getInstance().test(sirenCandidate)) {
            throw new SirenValidationException(ExceptionsDictionary.SIREN_VALIDATION_ERROR.getErrorCode(), ExceptionsDictionary.SIREN_VALIDATION_ERROR.getMessage());
        }
        return new Siren(sirenCandidate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Siren siren = (Siren) o;
        return Objects.equals(number, siren.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}

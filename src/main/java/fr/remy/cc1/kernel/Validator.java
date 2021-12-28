package fr.remy.cc1.kernel;

import fr.remy.cc1.kernel.error.ValidationException;

public interface Validator<T> {
    boolean test(T aspirant) throws ValidationException;
}

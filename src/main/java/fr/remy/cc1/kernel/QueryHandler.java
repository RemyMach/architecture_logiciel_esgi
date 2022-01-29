package fr.remy.cc1.kernel;

import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

public interface QueryHandler<Q extends Query, R> {
    R handle(Q query) throws Exception;
}

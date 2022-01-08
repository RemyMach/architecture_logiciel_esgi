package fr.remy.cc1.exposition.authentication;

import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;


public interface Tokens {

    void save(Token token, UserId userId);

    Token byUserId(UserId userId) throws NoSuchEntityException;

    TokenId nextIdentity();
}

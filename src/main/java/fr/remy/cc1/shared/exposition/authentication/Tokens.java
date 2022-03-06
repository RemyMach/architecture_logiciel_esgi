package fr.remy.cc1.shared.exposition.authentication;

import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;

import java.io.UnsupportedEncodingException;


public interface Tokens {

    void save(Token token, UserId userId);

    Token byUserId(UserId userId) throws NoSuchEntityException;

    TokenId nextIdentity(UserId userId) throws UnsupportedEncodingException;
}

package fr.remy.cc1.exposition.authentication;

import fr.remy.cc1.domain.user.Email;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.io.UnsupportedEncodingException;


public interface Tokens {

    void save(Token token, UserId userId);

    Token byUserId(UserId userId) throws NoSuchEntityException;

    TokenId nextIdentity(UserId userId) throws UnsupportedEncodingException;
}

package fr.remy.cc1.infrastructure.exceptions;

import fr.remy.cc1.kernel.error.ValidationException;

import java.util.Map;

public class InfrastructureExceptionsDictionary {
    public static final NoSuchEntityException USER_NOT_FOUND = new NoSuchEntityException("user_not_found", "the user specified is not found");
    public static final NoSuchEntityException TOKEN_NOT_FOUND = new NoSuchEntityException("token_not_found", "the pair user token doesn\'t match");

}

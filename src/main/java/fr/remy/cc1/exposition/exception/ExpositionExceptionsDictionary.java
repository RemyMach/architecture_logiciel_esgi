package fr.remy.cc1.exposition.exception;

import fr.remy.cc1.exposition.exception.authentication.AuthFailedException;
import fr.remy.cc1.exposition.exception.authentication.AuthRequiredException;

public class ExpositionExceptionsDictionary {
    public static final AuthFailedException AUTH_FAILED = new AuthFailedException("auth_failed" ,"your authentication has failed");
    public static final AuthRequiredException AUTH_REQUIRED = new AuthRequiredException("auth_required" ,"an authentication is required");
    public static final AuthRequiredException PASSWORD_EMAIL_AUTH_FAILED = new AuthRequiredException("password_email_auth_failed" ,"your password / login is invalid");

}

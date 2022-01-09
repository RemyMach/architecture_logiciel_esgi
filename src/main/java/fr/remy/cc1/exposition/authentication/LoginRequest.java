package fr.remy.cc1.exposition.authentication;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull(message = "email_required")
    public String email;

    @NotNull(message = "password_required")
    public String password;
}

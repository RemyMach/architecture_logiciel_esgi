package fr.remy.cc1.exposition.authentication;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull(message = "user_not_found")
    public String email;

    @NotNull(message = "user_not_found")
    public String password;
}

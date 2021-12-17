package fr.remy.cc1.exposition;

import fr.remy.cc1.domain.user.UserCategory;

import javax.validation.constraints.*;

public class UserRequest {

    @NotNull
    @NotBlank
    public String lastname;

    @NotNull
    @NotBlank
    public String firstname;

    @NotNull
    @NotBlank
    @Email
    public String email;

    @NotNull
    @NotBlank
    public String password;

    @NotNull
    @NotBlank
    public String userCategory;
}

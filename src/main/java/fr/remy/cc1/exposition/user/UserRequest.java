package fr.remy.cc1.exposition.user;

import javax.validation.constraints.*;

public class UserRequest {

    @NotNull(message = "lastname_empty_null")
    @NotBlank(message = "lastname_empty_null")
    public String lastname;

    @NotNull(message = "firstname_empty_null")
    @NotBlank(message = "firstname_empty_null")
    public String firstname;

    @NotNull(message = "email_empty_null")
    @NotBlank(message = "email_empty_null")
    public String email;

    @NotNull(message = "password_empty_null")
    @NotBlank(message = "password_empty_null")
    public String password;

    @NotNull(message = "user_category_empty_null")
    @NotBlank(message = "user_category_empty_null")
    public String userCategory;
}

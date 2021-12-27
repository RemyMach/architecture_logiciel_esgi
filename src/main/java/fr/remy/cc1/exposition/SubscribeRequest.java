package fr.remy.cc1.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SubscribeRequest {
    @NotNull(message = "9")
    @NotBlank(message = "9")
    public String lastname;

    @NotNull(message = "8")
    @NotBlank(message = "8")
    public String firstname;

    @NotNull(message = "10")
    @NotBlank(message = "10")
    public String email;

    @NotNull(message = "7")
    @NotBlank(message = "7")
    public String password;

    @NotNull(message = "11")
    @NotBlank(message = "11")
    public String userCategory;
}
}

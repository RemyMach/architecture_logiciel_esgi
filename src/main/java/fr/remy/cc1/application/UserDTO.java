package fr.remy.cc1.application;

import fr.remy.cc1.domain.user.UserCategory;
import fr.remy.cc1.domain.user.UserId;

public class UserDTO {
    public final UserId id;
    public final String lastname;
    public final String firstname;
    public final String email;

    public UserDTO(UserId id, String lastname, String firstname, String email) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    }

}
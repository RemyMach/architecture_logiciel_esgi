package fr.remy.cc1.application;

import fr.remy.cc1.domain.user.UserId;

public class UserDTO {
    public final UserId id;
    public final String lastname;
    public final String firstname;

    public UserDTO(UserId id, String lastname, String firstname) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
    }

}

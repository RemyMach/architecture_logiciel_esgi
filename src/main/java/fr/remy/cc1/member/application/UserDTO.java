package fr.remy.cc1.member.application;

import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.shared.domain.UserId;

public class UserDTO {
    public final UserId id;
    public final String lastname;
    public final String firstname;
    public final Email email;

    public UserDTO(UserId id, String lastname, String firstname, Email email) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    }

}

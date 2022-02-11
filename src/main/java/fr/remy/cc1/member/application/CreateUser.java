package fr.remy.cc1.member.application;

import fr.remy.cc1.member.domain.user.UserCategory;
import fr.remy.cc1.kernel.Command;

//TODO deprecated
@SuppressWarnings("all")
public class CreateUser implements Command {

    public final String lastname;
    public final String firstname;
    public final String email;
    public final String password;
    public final UserCategory userCategory;

    public CreateUser(String lastname, String firstname, String email, String password, UserCategory userCategory) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.userCategory = userCategory;
    }
}

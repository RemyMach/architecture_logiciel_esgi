package fr.remy.cc1.member.application;

import fr.remy.cc1.kernel.Command;

public class CreateTradesman implements Command {
    public final String lastname;
    public final String firstname;
    public final String email;
    public final String password;

    public CreateTradesman(String lastname, String firstname, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }
}

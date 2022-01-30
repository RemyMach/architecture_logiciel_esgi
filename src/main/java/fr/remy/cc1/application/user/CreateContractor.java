package fr.remy.cc1.application.user;


import fr.remy.cc1.domain.company.Company;
import fr.remy.cc1.kernel.Command;

public class CreateContractor implements Command {

    public final String lastname;
    public final String firstname;
    public final String email;
    public final String password;
    public final Company company;

    public CreateContractor(String lastname, String firstname, String email, String password, Company company) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.company = company;
    }
}

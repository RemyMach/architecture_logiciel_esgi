package fr.remy.cc1.member.application;


import fr.remy.cc1.kernel.Command;

public class CreateContractor implements Command {

    public final String lastname;
    public final String firstname;
    public final String email;
    public final String password;
    public final String companySiren;
    public final String companyName;

    public CreateContractor(String lastname, String firstname, String email, String password, String companySiren, String companyName) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.companySiren = companySiren;
        this.companyName = companyName;
    }
}

package fr.remy.cc1.domain.user;

import fr.remy.cc1.kernel.error.ValidationException;

public class UserCandidate {

    public final String lastname;
    public final String firstname;
    public final Email email;
    public final Password password;
    public final UserCategory userCategory;

    private UserCandidate(String lastname, String firstname, Email email, Password password, UserCategory userCategory) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.userCategory = userCategory;
    }

    public static UserCandidate of( String lastname, String firstname, Email email, Password password, UserCategory userCategory) throws ValidationException {
        UserCandidate userCandidate = new UserCandidate(lastname, firstname, email,  password, userCategory);
        if(ValidationUserCandidateEngine.getInstance().test(userCandidate)) {
            return userCandidate;
        }
        return null;
    }
}

package fr.remy.cc1.domain.user;

import fr.remy.cc1.kernel.error.ValidationException;

import java.util.Objects;

public final class UserCandidate {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCandidate that = (UserCandidate) o;
        return Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && userCategory == that.userCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, email, password, userCategory);
    }
}

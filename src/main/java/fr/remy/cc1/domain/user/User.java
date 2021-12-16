package fr.remy.cc1.domain.user;

public final class User {

    private final UserId userId;
    private final String lastname;
    private final String firstname;
    private final String email;
    private final String password;
    private final UserCategory userCategory;

    private User(UserId userId, String lastname, String firstname, String email, String password, UserCategory userCategory) {
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.userCategory = userCategory;
    }

    public static User of(UserId userId, String lastname, String firstname, String email, String password, UserCategory userCategory) {
        User user = new User(userId, lastname, firstname, email,  password, userCategory);
        if(ValidationUserEngine.getInstance().test(user)) {
            return user;
        }
        throw new IllegalArgumentException("the user fields are not valid");
    }

    public UserId getUserId() {
        return userId;
    }

    public String getEmail() { return email; }

    public String getPassword() { return password; }
}

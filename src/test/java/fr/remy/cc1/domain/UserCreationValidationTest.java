package fr.remy.cc1.domain;

import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ValidationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserCreationValidationTest {

    UserId stubUserId;

    @BeforeAll
    void init() {
        this.stubUserId = UserId.of(13239293);
    }

    @Test
    @DisplayName("should return an error because email and password are empty")
    void userHasNullPasswordAndEmail() {
        try {
            UserCandidate userCandidate = UserCandidate.of( "", "", new Email(""), new Password(""), UserCategoryCreator.getValueOf("TRADESMAN"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            fail( "Should have thrown an exception" );
        }catch (ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.EMAIL_NOT_VALID.getErrorCode());
        }
    }

    @Test
    @DisplayName("should return an error because email is empty")
    void userHasNullEmail() {
        try {
            UserCandidate userCandidate = UserCandidate.of( "", "", new Email(""), new Password("azertyuiop"),UserCategoryCreator.getValueOf("TRADESMAN"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            fail( "Should have thrown an exception" );
        }catch (ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.EMAIL_NOT_VALID.getErrorCode());
        }
    }

    @Test
    @DisplayName("should return an error because email has no domain")
    void userHasANEmailWithNoDomain() {
        try {
            UserCandidate userCandidate = UserCandidate.of( "", "", new Email("pomme@"), new Password(""), UserCategoryCreator.getValueOf("TRADESMAN"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            fail( "Should have thrown an exception" );
        }catch (ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.EMAIL_NOT_VALID.getErrorCode());
        }
    }

    @Test
    @DisplayName("should return an error because password haven't got an 8 length")
    void userHasAPasswordWithALengthLessThan8Characters() {
        try {
            String password = "aZert9!";
            UserCandidate userCandidate = UserCandidate.of( "", "", new Email("pomme@pomme.com"), new Password(password), UserCategoryCreator.getValueOf("TRADESMAN"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            fail( "Should have thrown an exception" );
        }catch (ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.PASSWORD_NOT_VALID.getErrorCode());
        }
    }

    @Test
    @DisplayName("should return an error because password has a length superior than 20 characters")
    void userHasAPasswordWithALengthSuperiorThan20Characters() {
        try {
            String stringRepeated = "b";
            String password = "aZert9!" + stringRepeated.repeat(14);
            UserCandidate userCandidate = UserCandidate.of( "", "", new Email("pomme@pomme.com"), new Password(password), UserCategoryCreator.getValueOf("TRADESMAN"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            fail( "Should have thrown an exception" );
        }catch (ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.PASSWORD_NOT_VALID.getErrorCode());
        }
    }

    @Test
    @DisplayName("should return an error because password has No Uppercase")
    void userHasAPasswordWithNoUppercase() {
        try {
            String password = "azerty9!";
            UserCandidate userCandidate = UserCandidate.of("", "", new Email("pomme@pomme.com"), new Password(password), UserCategoryCreator.getValueOf("TRADESMAN"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            fail( "Should have thrown an exception" );
        }catch (ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.PASSWORD_NOT_VALID.getErrorCode());
        }
    }

    @Test
    @DisplayName("should return an error because password has No Lowercase")
    void userHasAPasswordWithNoLowercase() {
        try {
            String password = "AZERTY9!";
            UserCandidate userCandidate = UserCandidate.of( "", "", new Email("pomme@pomme.com"), new Password(password), UserCategoryCreator.getValueOf("TRADESMAN"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            fail( "Should have thrown an exception" );
        }catch (ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.PASSWORD_NOT_VALID.getErrorCode());
        }
    }

    @Test
    @DisplayName("should return an error because password has No Number")
    void userHasAPasswordWithNoNumber() {
        try {
            String password = "aZertyo!";
            UserCandidate userCandidate = UserCandidate.of( "", "", new Email("pomme@pomme.com"), new Password(password), UserCategoryCreator.getValueOf("TRADESMAN"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            fail( "Should have thrown an exception" );
        }catch (ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.PASSWORD_NOT_VALID.getErrorCode());
        }
    }

    @Test
    @DisplayName("should return an error because password has No special charracters")
    void userHasAPasswordWithNoSpecialCharacters() {
        try {
            String password = "aZerty98";
            UserCandidate userCandidate = UserCandidate.of( "", "", new Email("pomme@pomme.com"), new Password(password),UserCategoryCreator.getValueOf("TRADESMAN"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            fail( "Should have thrown an exception" );
        }catch (ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.PASSWORD_NOT_VALID.getErrorCode());
        }
    }

    @Test
    @DisplayName("should return an error because pomme is not a user categrory")
    void userHasANotValidUserCategory() {
        try {
            String password = "aZerty98";
            UserCandidate userCandidate = UserCandidate.of( "", "", new Email("pomme@pomme.com"), new Password(password), UserCategoryCreator.getValueOf("pomme"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            fail( "Should have thrown an exception" );
        }catch (ValidationException userCategoryValidatorException) {
            assertEquals(userCategoryValidatorException.getErrorCode(), ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getErrorCode());
        }
    }

    @Test
    @DisplayName("should match the password because user have at least one Number, one LowerCase, one Uppercase, one specialCharacters")
    void userHasAValidEmailAndPassword() {
        try {
            String password = "aZertyo9!";
            UserCandidate userCandidate = UserCandidate.of(  "", "", new Email("pomme@pomme.com"), new Password(password), UserCategoryCreator.getValueOf("TRADESMAN"));
            User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
        }catch (ValidationException exception) {
            fail( "Should have not thrown an exception" );
        }
    }

    @Test
    @DisplayName("should match every special characters defined")
    void userHasAValidEmailAndPasswordWithSpecialCharacters() {
        try {
            String specialCharacters = "!@#&()–$:;',?/*~$^+=<>";
            for(int i = 0; i<specialCharacters.length(); i++) {
                String password = "aZertyo9";
                password += specialCharacters.charAt(i);
                UserCandidate userCandidate = UserCandidate.of( "", "", new Email("pomme@pomme.com"), new Password(password), UserCategoryCreator.getValueOf("TRADESMAN"));
                User user = User.of(stubUserId, userCandidate.firstname, userCandidate.lastname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
            }
        } catch (ValidationException exception) {
            fail( "Should not thrown an error" );
        }
    }
}

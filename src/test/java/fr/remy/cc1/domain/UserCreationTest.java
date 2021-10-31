package fr.remy.cc1.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserCreationTest {

    UserId stubUserId;

    @BeforeAll
    void init() {
        this.stubUserId = UserId.of(13239293);
    }

    @Test
    @DisplayName("should return an error because email and password are empty")
    void userHasNullPasswordAndEmail() {
        try {
            User user = User.of(stubUserId, "", "", "", "");
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Illegal arguments");
        }
    }

    @Test
    @DisplayName("should return an error because email is empty")
    void userHasNullEmail() {
        try {
            User user = User.of(stubUserId, "", "", "", "azertyuiop");
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Illegal arguments");
        }
    }

    @Test
    @DisplayName("should return an error because email has no domain")
    void userHasANEmailWithNoDomain() {
        try {
            User user = User.of(stubUserId, "", "", "pomme@", "");
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Illegal arguments");
        }
    }

    @Test
    @DisplayName("should return an error because password haven't got an 8 length")
    void userHasAPasswordWithALengthLessThan8Characters() {
        try {
            String password = "aZert9!";
            User user = User.of(stubUserId, "", "", "pomme@pomme.com", password);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Illegal arguments");
        }
    }

    @Test
    @DisplayName("should return an error because password has a length superior than 20 characters")
    void userHasAPasswordWithALengthSuperiorThan20Characters() {
        try {
            String stringRepeated = "b";
            String password = "aZert9!" + stringRepeated.repeat(14);
            User user = User.of(stubUserId, "", "", "pomme@pomme.com", password);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Illegal arguments");
        }
    }

    @Test
    @DisplayName("should return an error because password has No Uppercase")
    void userHasAPasswordWithNoUppercase() {
        try {
            String password = "azerty9!";
            User user = User.of(stubUserId, "", "", "pomme@pomme.com", password);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Illegal arguments");
        }
    }

    @Test
    @DisplayName("should return an error because password has No Lowercase")
    void userHasAPasswordWithNoLowercase() {
        try {
            String password = "AZERTY9!";
            User user = User.of(stubUserId, "", "", "pomme@pomme.com", password);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Illegal arguments");
        }
    }

    @Test
    @DisplayName("should return an error because password has No Number")
    void userHasAPasswordWithNoNumber() {
        try {
            String password = "aZertyo!";
            User user = User.of(stubUserId, "", "", "pomme@pomme.com", password);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Illegal arguments");
        }
    }

    @Test
    @DisplayName("should return an error because password has No special charracters")
    void userHasAPasswordWithNoSpecialCharacters() {
        try {
            String password = "aZerty98";
            User user = User.of(stubUserId, "", "", "pomme@pomme.com", password);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Illegal arguments");
        }
    }

    @Test
    @DisplayName("should match the password because user have at least one Number, one LowerCase, one Uppercase, one specialCharacters")
    void userHasAValidEmailAndPassword() {
        try {
            String password = "aZertyo9!";
            User user = User.of(stubUserId, "", "", "pomme@pomme.com", password);
        }catch (IllegalArgumentException e) {
            fail( "Should have thrown an exception" );
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
                User user = User.of(stubUserId, "", "", "pomme@pomme.com", password);
            }
        }catch (IllegalArgumentException e) {
            fail( "Should not thrown an exception" );
        }
    }
}

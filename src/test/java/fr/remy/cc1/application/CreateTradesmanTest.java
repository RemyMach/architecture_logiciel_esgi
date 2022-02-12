package fr.remy.cc1.application;

import fr.remy.cc1.member.application.CreateTradesman;
import fr.remy.cc1.member.application.CreateTradesmanCommandHandler;
import fr.remy.cc1.domain.UserCreationStub;
import fr.remy.cc1.subscription.domain.invoice.Invoices;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.member.domain.user.Tradesman.Tradesmans;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.subscription.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.member.infrastructure.tradesman.InMemoryTradesmans;
import fr.remy.cc1.member.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.member.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CreateTradesmanTest {
    Users users;
    Tradesmans tradesmans;
    UserId myUserIdStub;
    Invoices invoices;

    String lastnameStub;
    String firstnameStub;
    String emailStub;
    String passwordStub;
    String userCategoryStub;

    CreateTradesmanCommandHandler createTradesmanCommandHandler;


    @BeforeEach
    void initStubValues() {
        this.lastnameStub = "Machavoine";
        this.firstnameStub = "Rémy";
        this.emailStub = "pomme@pomme.fr";
        this.passwordStub = "aZertyu9?";
        this.userCategoryStub = "TRADESMAN";

        this.tradesmans = new InMemoryTradesmans(new ConcurrentHashMap<>());
        this.users = new InMemoryUsers(new ConcurrentHashMap<>());
        this.myUserIdStub = users.nextIdentity();
        this.invoices = new InMemoryInvoices();
        UserCreationStub.initUserCreationTest(this.users, this.invoices);
        this.createTradesmanCommandHandler = new CreateTradesmanCommandHandler(users, tradesmans, UserCreationEventBus.getInstance());
    }

    @Test
    @DisplayName("should register the user and send an email")
    void userIsValid() throws ValidationException, NoSuchEntityException {

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        assertEquals(this.users.findAll().size(), 0);

        CreateTradesman createTradesman = new CreateTradesman(lastnameStub, firstnameStub, emailStub, passwordStub);
        UserId userId = createTradesmanCommandHandler.handle(createTradesman);

        assertEquals(this.users.findAll().size(), 1);
        assertEquals(this.users.byId(userId).getEmail().email, emailStub);
        assertEquals(MockEmailSender.getInstance().getCountMail(), 1);
    }

    @Test
    @DisplayName("should not register the user and don't send the email because password is to short")
    void userIsNotValid() throws ValidationException {

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        assertEquals(this.users.findAll().size(), 0);

        try {
            this.passwordStub = "short";
            CreateTradesman createTradesman = new CreateTradesman(lastnameStub, firstnameStub, emailStub, passwordStub);
            UserId userId = createTradesmanCommandHandler.handle(createTradesman);
            fail("the test should thrown an error");
        }catch(ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.PASSWORD_NOT_VALID.getErrorCode());
            assertEquals(this.users.findAll().size(), 0);
            assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        }
    }
}

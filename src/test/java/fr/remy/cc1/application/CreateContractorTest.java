package fr.remy.cc1.application;

import fr.remy.cc1.member.application.CreateContractor;
import fr.remy.cc1.member.application.CreateContractorCommandHandler;
import fr.remy.cc1.domain.UserCreationStub;
import fr.remy.cc1.subscription.domain.invoice.Invoices;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.member.domain.user.UserId;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.member.domain.user.contractor.Contractors;
import fr.remy.cc1.member.infrastructure.contractor.InMemory.InMemoryContractors;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.subscription.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.member.infrastructure.user.InMemory.InMemoryUsers;
import fr.remy.cc1.member.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CreateContractorTest {

    Users users;
    Contractors contractors;
    UserId myUserIdStub;
    Invoices invoices;

    String lastnameStub;
    String firstnameStub;
    String emailStub;
    String passwordStub;
    String userCategoryStub;
    String companySiren;
    String companyName;

    CreateContractorCommandHandler createContractorCommandHandler;


    @BeforeEach
    void initStubValues() {
        this.lastnameStub = "Machavoine";
        this.firstnameStub = "Rémy";
        this.emailStub = "pomme@pomme.fr";
        this.passwordStub = "aZertyu9?";
        this.userCategoryStub = "TRADESMAN";
        this.companySiren = "001 223 445";
        this.companyName = "Lucas";

        this.contractors = new InMemoryContractors(new ConcurrentHashMap<>());
        this.users = new InMemoryUsers(new ConcurrentHashMap<>());
        this.myUserIdStub = users.nextIdentity();
        this.invoices = new InMemoryInvoices();
        UserCreationStub.initUserCreationTest(this.users, this.invoices);
        this.createContractorCommandHandler = new CreateContractorCommandHandler(users, contractors, UserCreationEventBus.getInstance());
    }

    @Test
    @DisplayName("should register the user and send an email")
    void userIsValid() throws ValidationException, NoSuchEntityException {

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        assertEquals(this.users.findAll().size(), 0);

        CreateContractor createContractor = new CreateContractor(lastnameStub, firstnameStub, emailStub, passwordStub, companySiren, companyName);
        UserId userId = createContractorCommandHandler.handle(createContractor);

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
            CreateContractor createContractor = new CreateContractor(lastnameStub, firstnameStub, emailStub, passwordStub, companySiren, companyName);
            UserId userId = createContractorCommandHandler.handle(createContractor);
            fail("the test should thrown an error");
        }catch(ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.PASSWORD_NOT_VALID.getErrorCode());
            assertEquals(this.users.findAll().size(), 0);
            assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        }
    }
}

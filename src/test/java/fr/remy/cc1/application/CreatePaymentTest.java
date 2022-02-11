package fr.remy.cc1.application;

import fr.remy.cc1.subscription.application.payment.CreatePayment;
import fr.remy.cc1.subscription.application.payment.CreatePaymentCommandHandler;
import fr.remy.cc1.domain.UserCreationStub;
import fr.remy.cc1.subscription.domain.invoice.Invoices;
import fr.remy.cc1.subscription.domain.creditcard.CreditCards;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccounts;
import fr.remy.cc1.member.domain.user.*;
import fr.remy.cc1.subscription.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.subscription.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class CreatePaymentTest {
    Users users;
    UserId myUserIdStub;
    Invoices invoices;
    CreditCards creditCards;
    PaypalAccounts paypalAccounts;

    String paymentChoiceStub;
    String creditCardNumber;
    int creditCardExpiryDate;
    int creditCardSecurityCode;
    String creditCardName;
    UserId userId;

    CreatePaymentCommandHandler createPaymentCommandHandler;


    @BeforeEach
    void initStubValues() {
        paymentChoiceStub = "CreditCard";
        creditCardNumber = "1234567262";
        creditCardExpiryDate = 1203;
        creditCardSecurityCode = 321;
        creditCardName = "Pomme";
        userId = UserId.of(1);


        this.users = new InMemoryUsers();
        this.myUserIdStub = users.nextIdentity();
        this.invoices = new InMemoryInvoices();
        this.creditCards = new InMemoryCreditCards();

        UserCreationStub.initUserCreationTest(this.users, this.invoices);
        this.createPaymentCommandHandler = new CreatePaymentCommandHandler(creditCards, paypalAccounts, users);
    }

    @Test
    @DisplayName("should register the creditCard to the user")
    void paymentIsValid() throws Exception {

        User user = User.of(userId, "Machavoine", "Rémy", new Email("pomme@pomme.fr"), new Password("aZertyu9?"), UserCategory.TRADESMAN);
        this.users.save(user);

        try {
            creditCards.findByUserId(userId);
            fail("should Fail");
        }catch (Exception e) { }

        CreatePayment createPayment = new CreatePayment(
                paymentChoiceStub,
                creditCardNumber,
                creditCardExpiryDate,
                creditCardSecurityCode,
                creditCardName,
                userId
        );
        createPaymentCommandHandler.handle(createPayment);

        try {
            creditCards.findByUserId(userId);
        }catch (Exception e) {
            fail("should not Fail");
        }
    }

    @Test
    @DisplayName("should not register the creditCard because the payment choice os not valid")
    void paymentChoiceIsNotSupported() throws Exception {

        User user = User.of(userId, "Machavoine", "Rémy", new Email("pomme@pomme.fr"), new Password("aZertyu9?"), UserCategory.TRADESMAN);
        this.users.save(user);
        paymentChoiceStub = "Stripe";

        try {
            creditCards.findByUserId(userId);
            fail("should Fail");
        }catch (Exception e) { }

        try {
            CreatePayment createPayment = new CreatePayment(
                    paymentChoiceStub,
                    creditCardNumber,
                    creditCardExpiryDate,
                    creditCardSecurityCode,
                    creditCardName,
                    userId
            );
            createPaymentCommandHandler.handle(createPayment);
            fail("should Fail");
        }catch (Exception e) {
            try {
                creditCards.findByUserId(userId);
                fail("should Fail");
            }catch (Exception exception) { }
        }
    }
}

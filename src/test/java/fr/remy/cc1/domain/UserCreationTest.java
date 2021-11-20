package fr.remy.cc1.domain;
import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.domain.payment.*;
import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.domain.payment.creditcard.CreditCardId;
import fr.remy.cc1.domain.payment.creditcard.CreditCards;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.UserService;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.domain.payment.creditcard.SaveCreditCardEvent;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserCreationTest {

    Users users;
    UserId myUserIdStub;
    CreditCards creditCards;
    CreditCardId creditCardIdStub;
    Invoices invoices;

    CurrencyBuild currencyBuild;
    PaymentBuild paymentBuild;

    String lastnameStub;
    String firstnameStub;
    String emailStub;
    String passwordStub;

    String priceSubscriptionOfferStub;
    int discountPercentageStub;

    String currencyChoiceStub;
    String paymentChoiceStub;
    boolean saveCreditCardStub;


    @BeforeEach
    void initStubValues() {
        this.lastnameStub = "Machavoine";
        this.firstnameStub = "Rémy";
        this.emailStub = "pomme@pomme.fr";
        this.passwordStub = "aZertyu9?";

        this.priceSubscriptionOfferStub = "12.05";
        this.discountPercentageStub = 10;

        this.currencyChoiceStub = "EUR";
        this.paymentChoiceStub = "CreditCard";
        this.saveCreditCardStub = true;

        this.users = new InMemoryUsers();
        this.myUserIdStub = users.nextIdentity();
        this.creditCards = new InMemoryCreditCards();
        this.creditCardIdStub = creditCards.nextIdentity();
        this.invoices = new InMemoryInvoices();
        UserCreationStub.initUserCreationTest(this.users, this.invoices, this.creditCards);

        this.currencyBuild = new CurrencyBuild();
        this.paymentBuild = new PaymentBuild();
    }

    @Test
    @DisplayName("should return an exception because currency is not EUR or USD")
    void userHasNotAValidCurrency() {

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);

        currencyChoiceStub = "GBP";

        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(priceSubscriptionOfferStub), discountPercentageStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,1234567262, 1203, 321, "POMME");
        Payer payer = new Payer(creditCard, null);

        try {
            User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub);
            this.currencyBuild.getCurrencyOf(currencyChoiceStub);
            createUser(user, users, payer, currencyChoiceStub, paymentChoiceStub, saveCreditCardStub, subscriptionOffer);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), currencyBuild.getExceptionMessage());
            assertEquals(this.users.findAll().size(), 0);
            assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        }
    }

    @Test
    @DisplayName("should return an exception because payment is not Paypal or CreditCard")
    void userHasNotAValidPaymentMethod() {

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);

        paymentChoiceStub = "Stripe";

        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(priceSubscriptionOfferStub), discountPercentageStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,1234567262, 1203, 321, "POMME");
        Payer payer = new Payer(creditCard, null);

        try {
            User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub);
            this.currencyBuild.getCurrencyOf(currencyChoiceStub);
            createUser(user, users, payer, currencyChoiceStub, paymentChoiceStub, saveCreditCardStub, subscriptionOffer);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), paymentBuild.getExceptionMessage());
            assertEquals(this.users.findAll().size(), 0);
            assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        }
    }

    @Test
    @DisplayName("should return an exception because user has a not valid email")
    void userHasNoValidEmail() {

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);

        emailStub = "pomme";

        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(priceSubscriptionOfferStub), discountPercentageStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,1234567262, 1203, 321, "POMME");
        Payer payer = new Payer(creditCard, null);

        try {
            User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub);
            this.currencyBuild.getCurrencyOf(currencyChoiceStub);
            createUser(user, users, payer, currencyChoiceStub, paymentChoiceStub, saveCreditCardStub, subscriptionOffer);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException exception) {
            assertEquals(exception.getMessage(), "the user fields are not valid");
            assertEquals(this.users.findAll().size(), 0);
            assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        }
    }

    @Test
    @DisplayName("should register user, credit card and Invoice")
    void userIsValid() {

        assertEquals(this.invoices.findAll().size(), 0);
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(priceSubscriptionOfferStub), discountPercentageStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,1234567262, 1203, 321, "POMME");
        Payer payer = new Payer(creditCard, null);

        User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub);
        this.currencyBuild.getCurrencyOf(currencyChoiceStub);
        createUser(user, users, payer, currencyChoiceStub, paymentChoiceStub, saveCreditCardStub, subscriptionOffer);
        assertEquals(users.byId(myUserIdStub), user);
        assertEquals(this.invoices.findAll().size(), 1);
        assertEquals(this.users.getSubscriptionOffer(myUserIdStub), subscriptionOffer);
        assertEquals(this.users.byId(myUserIdStub), user);
        assertEquals(this.creditCards.byId(this.creditCardIdStub), creditCard);
        assertEquals(MockEmailSender.getInstance().getCountMail(), 2);
    }

    @Test
    @DisplayName("should register user, and Invoice but not save credit card")
    void userIsValidAndCreditCardIsNotSave() {

        this.saveCreditCardStub = false;
        assertEquals(this.invoices.findAll().size(), 0);
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(priceSubscriptionOfferStub), discountPercentageStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,1234567262, 1203, 321, "POMME");
        Payer payer = new Payer(creditCard, null);

        User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub);
        this.currencyBuild.getCurrencyOf(currencyChoiceStub);
        createUser(user, users, payer, currencyChoiceStub, paymentChoiceStub, saveCreditCardStub, subscriptionOffer);
        assertEquals(users.byId(myUserIdStub), user);
        assertEquals(this.invoices.findAll().size(), 1);
        assertEquals(this.users.getSubscriptionOffer(myUserIdStub), subscriptionOffer);
        assertEquals(this.users.byId(myUserIdStub), user);
        assertEquals(this.creditCards.byId(this.creditCardIdStub), null);
    }

    // mettre un test comme on save pas la credit card

    private void createUser(
            User user,
            Users users,
            Payer payer,
            String currency,
            String paymentMethod,
            boolean saveCreditCard,
            SubscriptionOffer subscriptionOffer
    ) {
        Payment payment = this.paymentBuild.getPaymentOf(paymentMethod, payer);

        UserService userService = new UserService(users);
        PaymentService paymentService = new PaymentService(payment);

        paymentService.paySubscription(subscriptionOffer,  Currency.valueOf(currency), user);
        userService.create(user);
        if(saveCreditCard)
            UserCreationEventBus.getInstance().send(SaveCreditCardEvent.of(payer.getCreditCard(), user));
    }
}

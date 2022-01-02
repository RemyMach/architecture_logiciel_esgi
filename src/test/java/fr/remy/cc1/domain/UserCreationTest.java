package fr.remy.cc1.domain;
import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.domain.payment.*;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.kernel.error.ValidationException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserCreationTest {

    Users users;
    UserId myUserIdStub;
    CreditCards creditCards;
    CreditCardService creditCardService;
    CreditCardId creditCardIdStub;
    Invoices invoices;

    CurrencyCreator currencyCreator;
    UserCategoryCreator userCategoryCreator;
    String lastnameStub;
    String firstnameStub;
    String emailStub;
    String passwordStub;

    String priceSubscriptionOfferStub;
    int discountPercentageStub;

    String currencyChoiceStub;
    String userCategoryChoiceStub;
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
        this.userCategoryChoiceStub = "TRADESMAN";
        this.saveCreditCardStub = true;

        this.users = new InMemoryUsers();
        this.myUserIdStub = users.nextIdentity();
        this.creditCards = new InMemoryCreditCards();
        this.creditCardIdStub = creditCards.nextIdentity();
        this.invoices = new InMemoryInvoices();
        this.creditCardService = new CreditCardService(this.creditCards);
        UserCreationStub.initUserCreationTest(this.users, this.invoices, this.creditCardService);

        this.currencyCreator = new CurrencyCreator();
        this.userCategoryCreator = new UserCategoryCreator();
    }

    @Test
    @DisplayName("should return an exception because currency is not EUR or USD")
    void userHasNotAValidCurrency() {

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);

        currencyChoiceStub = "GBP";

        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(priceSubscriptionOfferStub), discountPercentageStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,"1234567262", 1203, 321, "POMME");
        Payer payer = new Payer(creditCard, null);

        try {
            User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub, this.userCategoryCreator.getValueOf(this.userCategoryChoiceStub));
            this.currencyCreator.getValueOf(currencyChoiceStub);
            createUser(user, users, payer, currencyChoiceStub, saveCreditCardStub, subscriptionOffer);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException | ValidationException e) {
            assertEquals(e.getMessage(), currencyCreator.getExceptionMessage());
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
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,"1234567262", 1203, 321, "POMME");
        Payer payer = new Payer(creditCard, null);

        try {
            User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub, this.userCategoryCreator.getValueOf(this.userCategoryChoiceStub));
            this.currencyCreator.getValueOf(currencyChoiceStub);
            createUser(user, users, payer, currencyChoiceStub, saveCreditCardStub, subscriptionOffer);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException | ValidationException exception) {
            assertEquals(exception.getMessage(), "the user fields are not valid");
            assertEquals(this.users.findAll().size(), 0);
            assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        }
    }

    @Test
    @DisplayName("should register user, credit card and Invoice")
    void userIsValid() throws ValidationException {

        assertEquals(this.invoices.findAll().size(), 0);
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(priceSubscriptionOfferStub), discountPercentageStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,"1234567262", 1203, 321, "POMME");
        Payer payer = new Payer(creditCard, null);

        User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub, this.userCategoryCreator.getValueOf(this.userCategoryChoiceStub));
        this.currencyCreator.getValueOf(currencyChoiceStub);
        createUser(user, users, payer, currencyChoiceStub, saveCreditCardStub, subscriptionOffer);
        assertEquals(users.byId(myUserIdStub), user);
        assertEquals(this.invoices.findAll().size(), 1);
        assertEquals(this.users.getSubscriptionOffer(myUserIdStub), subscriptionOffer);
        assertEquals(this.users.byId(myUserIdStub), user);
        assertEquals(this.creditCards.byId(this.creditCardIdStub), creditCard);
        assertEquals(MockEmailSender.getInstance().getCountMail(), 2);
    }

    @Test
    @DisplayName("should register user, and Invoice but not save credit card")
    void userIsValidAndCreditCardIsNotSave() throws ValidationException {

        this.saveCreditCardStub = false;
        assertEquals(this.invoices.findAll().size(), 0);
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(priceSubscriptionOfferStub), discountPercentageStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,"1234567262", 1203, 321, "POMME");
        Payer payer = new Payer(creditCard, null);

        User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub, this.userCategoryCreator.getValueOf(this.userCategoryChoiceStub));
        this.currencyCreator.getValueOf(currencyChoiceStub);
        createUser(user, users, payer, currencyChoiceStub, saveCreditCardStub, subscriptionOffer);
        assertEquals(users.byId(myUserIdStub), user);
        assertEquals(this.invoices.findAll().size(), 1);
        assertEquals(this.users.getSubscriptionOffer(myUserIdStub), subscriptionOffer);
        assertEquals(this.users.byId(myUserIdStub), user);
        assertEquals(this.creditCards.byId(this.creditCardIdStub), null);
    }

    private void createUser(
            User user,
            Users users,
            Payer payer,
            String currency,
            boolean saveCreditCard,
            SubscriptionOffer subscriptionOffer
    ) {
        Payment payment = PaymentDirector.createCreditCardPayment(payer.getCreditCard(), PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                List.of(new CreditCardChecker(), new CreditCardApproveTradesman(), new CreditCardContractor())));

        UserService userService = new UserService(users);
        PaymentService paymentService = new PaymentService(payment);

        paymentService.paySubscription(subscriptionOffer,  Currency.valueOf(currency), user);
        userService.create(user);
        if(saveCreditCard)
            UserCreationEventBus.getInstance().send(SaveCreditCardEvent.of(payer.getCreditCard(), user));
    }
}

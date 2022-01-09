package fr.remy.cc1.application;

import fr.remy.cc1.domain.UserCreationStub;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.user.Email;
import fr.remy.cc1.domain.user.Password;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import org.junit.jupiter.api.BeforeEach;

public class CreateUserTest {
    Users users;
    UserId myUserIdStub;
    Invoices invoices;

    String lastnameStub;
    String firstnameStub;
    Email emailStub;
    Password passwordStub;

    CreateUserCommandHandler createUserCommandHandler;


    @BeforeEach
    void initStubValues() {
        this.lastnameStub = "Machavoine";
        this.firstnameStub = "Rémy";
        this.emailStub = new Email("pomme@pomme.fr");
        this.passwordStub = new Password("aZertyu9?");


        this.users = new InMemoryUsers();
        this.myUserIdStub = users.nextIdentity();
        this.invoices = new InMemoryInvoices();
        UserCreationStub.initUserCreationTest(this.users, this.invoices);
        this.createUserCommandHandler = new CreateUserCommandHandler(users, UserCreationEventBus.getInstance());
    }

    /*@Test
    @DisplayName("should return an exception because currency is not EUR or USD")
    void userHasNotAValidCurrency() {

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);

        currencyChoiceStub = "GBP";

        try {
            User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub, this.userCategoryCreator.getValueOf(this.userCategoryChoiceStub));
            CreditCard creditCard = CreditCard.of(this.creditCardIdStub,"1234567262", 1203, 321, "POMME", user.getUserId());
            this.currencyCreator.getValueOf(currencyChoiceStub);
            SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new Money(new BigDecimal(priceSubscriptionOfferStub), Currency.valueOf(currencyChoiceStub)) , discountPercentageStub);
            createUser2(user, users, creditCard, saveCreditCardStub, subscriptionOffer);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException | ValidationException e) {
            assertEquals(e.getMessage(), ExceptionsDictionary.CURRENCY_NOT_PRESENT.getErrorCode());
            assertEquals(this.users.findAll().size(), 0);
            assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        }
    }*/

    /*@Test
    @DisplayName("should return an exception because user has a not valid email")
    void userHasNoValidEmail() {

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);

        emailStub = new Email("pomme");

        try {
            User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub, this.userCategoryCreator.getValueOf(this.userCategoryChoiceStub));
            this.currencyCreator.getValueOf(currencyChoiceStub);
            SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new Money(new BigDecimal(priceSubscriptionOfferStub), Currency.valueOf(currencyChoiceStub)), discountPercentageStub);
            CreditCard creditCard = CreditCard.of(this.creditCardIdStub,"1234567262", 1203, 321, "POMME", user.getUserId());
            createUser2(user, users, creditCard, saveCreditCardStub, subscriptionOffer);
            fail( "Should have thrown an exception" );
        }catch (IllegalArgumentException | ValidationException exception) {
            assertEquals(exception.getMessage(), "the user fields are not valid");
            assertEquals(this.users.findAll().size(), 0);
            assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        }
    }

    @Test
    @DisplayName("should register user, credit card and Invoice")
    void userIsValid() throws ValidationException, NoSuchEntityException {

        assertEquals(this.invoices.findAll().size(), 0);

        User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub, this.userCategoryCreator.getValueOf(this.userCategoryChoiceStub));
        this.currencyCreator.getValueOf(currencyChoiceStub);
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new Money(new BigDecimal(priceSubscriptionOfferStub), Currency.valueOf(currencyChoiceStub)), discountPercentageStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,"1234567262", 1203, 321, "POMME", user.getUserId());
        createUser2(user, users, creditCard, saveCreditCardStub, subscriptionOffer);
        assertEquals(users.byId(myUserIdStub), user);
        assertEquals(this.invoices.findAll().size(), 1);
        assertEquals(this.users.getSubscriptionOffer(myUserIdStub), subscriptionOffer);
        assertEquals(this.users.byId(myUserIdStub), user);
        assertEquals(this.creditCards.byId(this.creditCardIdStub), creditCard);
        assertEquals(MockEmailSender.getInstance().getCountMail(), 2);
    }

    @Test
    @DisplayName("should register user, and Invoice but not save credit card")
    void userIsValidAndCreditCardIsNotSave() throws ValidationException, NoSuchEntityException {

        this.saveCreditCardStub = false;
        assertEquals(this.invoices.findAll().size(), 0);
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new Money(new BigDecimal(priceSubscriptionOfferStub), Currency.valueOf(currencyChoiceStub)), discountPercentageStub);


        User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub, this.userCategoryCreator.getValueOf(this.userCategoryChoiceStub));
        this.currencyCreator.getValueOf(currencyChoiceStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,"1234567262", 1203, 321, "POMME", user.getUserId());
        //createUser2(user, users, creditCard, saveCreditCardStub, subscriptionOffer);
        assertEquals(users.byId(myUserIdStub), user);
        assertEquals(this.invoices.findAll().size(), 1);
        assertEquals(this.users.getSubscriptionOffer(myUserIdStub), subscriptionOffer);
        assertEquals(this.users.byId(myUserIdStub), user);
        assertEquals(this.creditCards.byId(this.creditCardIdStub), null);
    }*/
}

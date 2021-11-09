package fr.remy.cc1.domain;
import fr.remy.cc1.domain.payment.CreditCardId;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.payment.PaymentBuild;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.UserService;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.UserCreationEventBus;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.creditcards.SaveCreditCardEvent;
import fr.remy.cc1.infrastructure.users.InMemoryUsers;
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

    String lastnameStub;
    String firstnameStub;
    String emailStub;
    String passwordStub;

    String priceSubscriptionOfferStub;
    int discountPercentageStub;

    String currencyChoiceStub;
    String paymentChoiceStub;
    boolean saveCreditCardStub;
    SubscriptionOffer subscriptionOffer;

    @BeforeAll
    void initStubSingletons() {
        UserCreationStub.initUserCreationTest();
    }

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
    }

    @Test
    @DisplayName("should return an exception because currency is not EUR or USD")
    void userHasNotAValidCurrency() {

        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(priceSubscriptionOfferStub), discountPercentageStub);


        User user = User.of(this.myUserIdStub, lastnameStub, firstnameStub, emailStub, passwordStub);
        CurrencyValidator.getInstance().test(currencyChoiceStub);
        CreditCard creditCard = CreditCard.of(this.creditCardIdStub,1234567262, 1203, 321, "POMME");
        createUser(user, users, creditCard, currencyChoiceStub, paymentChoiceStub, saveCreditCardStub, subscriptionOffer);
    }

    private static void createUser(
            User user,
            Users users,
            CreditCard creditCard, String currency,
            String paymentMethod,
            boolean saveCreditCard,
            SubscriptionOffer subscriptionOffer
    ) {
        PaymentBuild paymentBuild = new PaymentBuild();
        Payment payment = paymentBuild.getPaymentOf(paymentMethod, creditCard);

        UserService userService = new UserService(users);
        PaymentService paymentService = new PaymentService(payment);

        paymentService.paySubscription(subscriptionOffer,  Currency.valueOf(currency), user);
        userService.create(user);
        if(saveCreditCard)
            UserCreationEventBus.getInstance().send(SaveCreditCardEvent.of(creditCard, user));
    }
}

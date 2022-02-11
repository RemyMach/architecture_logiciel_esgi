package fr.remy.cc1.application;

import fr.remy.cc1.subscription.application.CreateSubscriptionOffer;
import fr.remy.cc1.subscription.application.CreateSubscriptionOfferCommandHandler;
import fr.remy.cc1.domain.UserCreationStub;
import fr.remy.cc1.member.domain.customer.SubscriptionOffer;
import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.invoice.Invoices;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.subscription.domain.PaymentState;
import fr.remy.cc1.subscription.domain.creditcard.CreditCard;
import fr.remy.cc1.subscription.domain.creditcard.CreditCards;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccounts;
import fr.remy.cc1.member.domain.user.*;
import fr.remy.cc1.subscription.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.subscription.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.error.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CreateSubscriptionOfferTest {

    Users users;
    UserId myUserIdStub;
    Invoices invoices;
    CreditCards creditCards;
    PaypalAccounts paypalAccounts;

    int discountPercentageStub;
    BigDecimal priceSubscriptionOfferStub;
    String currencyChoiceStub;
    String paymentChoiceStub;
    UserId userId;

    CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler;


    @BeforeEach
    void initStubValues() {
        paymentChoiceStub = "CreditCard";
        discountPercentageStub = 10;
        currencyChoiceStub = "EUR";
        priceSubscriptionOfferStub = new BigDecimal(10);
        userId = UserId.of(1);
        this.users = new InMemoryUsers();
        this.myUserIdStub = users.nextIdentity();
        this.invoices = new InMemoryInvoices();
        this.creditCards = new InMemoryCreditCards();

        UserCreationStub.initUserCreationTest(this.users, this.invoices);
        this.createSubscriptionOfferCommandHandler = new CreateSubscriptionOfferCommandHandler(creditCards, paypalAccounts, users, UserCreationEventBus.getInstance());
    }

    @Test
    @DisplayName("should register the subscription with one payment success")
    void userIsValid() throws Exception {

        User user = User.of(userId, "Machavoine", "Rémy", new Email("pomme@pomme.fr"), new Password("aZertyu9?"), UserCategory.TRADESMAN);
        CreditCard creditCard = CreditCard.of(creditCards.nextIdentity(),"138416381", 1213, 321, "POMME", userId);
        this.users.save(user);
        this.creditCards.save(creditCard, userId);

        assertEquals(users.getSubscriptionOffer(user.getUserId()), null);
        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);


        CreateSubscriptionOffer createSubscriptionOffer = new CreateSubscriptionOffer(
                discountPercentageStub,
                priceSubscriptionOfferStub,
                userId,
                currencyChoiceStub,
                paymentChoiceStub
        );
        createSubscriptionOfferCommandHandler.handle(createSubscriptionOffer);

        try {
            SubscriptionOffer subscriptionOffer = users.getSubscriptionOffer(user.getUserId());
            List<Invoice> invoiceList = subscriptionOffer.getInvoiceList();
            assertEquals(invoiceList.size(), 1);
            assertEquals(invoiceList.get(0).getPaymentState(), PaymentState.VALIDATE);
            assertEquals(MockEmailSender.getInstance().getCountMail(), 1);
        }catch (Exception e) {
            fail("should not fail one subscription register");
        }

    }

    @Test
    @DisplayName("should register the subscription with one payment failed")
    void paymentCreditCardSecurityCodeNotValid() throws Exception {

        User user = User.of(userId, "Machavoine", "Rémy", new Email("pomme@pomme.fr"), new Password("aZertyu9?"), UserCategory.TRADESMAN);
        CreditCard creditCard = CreditCard.of(creditCards.nextIdentity(),"138416381", 1213, 420, "POMME", userId);
        this.users.save(user);
        this.creditCards.save(creditCard, userId);

        assertEquals(users.getSubscriptionOffer(user.getUserId()), null);

        try {
            CreateSubscriptionOffer createSubscriptionOffer = new CreateSubscriptionOffer(
                    discountPercentageStub,
                    priceSubscriptionOfferStub,
                    userId,
                    currencyChoiceStub,
                    paymentChoiceStub
            );
            createSubscriptionOfferCommandHandler.handle(createSubscriptionOffer);
            fail("should  fail because credit card security code not valid");
        }catch (ValidationException validationException) {
            try {
                SubscriptionOffer subscriptionOffer = users.getSubscriptionOffer(user.getUserId());
                List<Invoice> invoiceList = subscriptionOffer.getInvoiceList();
                assertEquals(invoiceList.size(), 1);
                assertEquals(invoiceList.get(0).getPaymentState(), PaymentState.REJECTED);
                assertEquals(MockEmailSender.getInstance().getCountMail(), 1);
            }catch (Exception e) {
                fail("should not fail one subscription register");
            }
        }


    }
}

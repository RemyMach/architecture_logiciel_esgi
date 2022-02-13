package fr.remy.cc1.application;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.infrastructure.InMemory.SubscriptionInvoiceData;
import fr.remy.cc1.infrastructure.InMemory.UserSubscriptionsData;
import fr.remy.cc1.infrastructure.InMemory.UsersData;
import fr.remy.cc1.subscription.application.CreateSubscriptionOffer;
import fr.remy.cc1.subscription.application.CreateSubscriptionOfferCommandHandler;
import fr.remy.cc1.domain.UserCreationStub;
import fr.remy.cc1.subscription.domain.SubscriptionOffers;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;
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
import fr.remy.cc1.member.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.member.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.subscription.infrastructure.subscriptions.InMemorySubscriptionOffers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CreateSubscriptionOfferTest {

    Users users;
    UserId myUserIdStub;
    Invoices invoices;
    SubscriptionOffers subscriptionOffers;
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
        UsersData.setup(new ConcurrentHashMap<>());
        UserSubscriptionsData.setup(new ConcurrentHashMap<>());
        this.users = new InMemoryUsers(UsersData.getInstance().data, UserSubscriptionsData.getInstance().data);
        this.myUserIdStub = users.nextIdentity();
        SubscriptionInvoiceData.setup(new ConcurrentHashMap<>());
        this.invoices = new InMemoryInvoices(SubscriptionInvoiceData.getInstance().data);
        this.creditCards = new InMemoryCreditCards();
        this.subscriptionOffers = new InMemorySubscriptionOffers(new ConcurrentHashMap<>(),UserSubscriptionsData.getInstance().data, SubscriptionInvoiceData.getInstance().data);

        UserCreationStub.initUserCreationTest(this.users, this.invoices);
        this.createSubscriptionOfferCommandHandler = new CreateSubscriptionOfferCommandHandler(creditCards, paypalAccounts,subscriptionOffers, users, UserCreationEventBus.getInstance());
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
            List<Invoice> invoiceList = this.invoices.findBySubscriptionOfferId(subscriptionOffer.getSubscriptionOfferId());
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
                List<Invoice> invoiceList = this.invoices.findBySubscriptionOfferId(subscriptionOffer.getSubscriptionOfferId());
                assertEquals(invoiceList.size(), 1);
                assertEquals(invoiceList.get(0).getPaymentState(), PaymentState.REJECTED);
                assertEquals(MockEmailSender.getInstance().getCountMail(), 1);
            }catch (Exception e) {
                fail("should not fail one subscription register");
            }
        }


    }
}

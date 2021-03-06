package fr.remy.cc1.application;

import fr.remy.cc1.shared.domain.User;
import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.infrastructure.InMemory.SubscriptionInvoiceData;
import fr.remy.cc1.shared.infrastructure.InMemory.UserSubscriptionsData;
import fr.remy.cc1.shared.infrastructure.InMemory.UsersData;
import fr.remy.cc1.subscription.domain.SubscriptionOffers;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOfferId;
import fr.remy.cc1.subscription.infrastructure.subscriptions.InMemorySubscriptionOffers;
import fr.remy.cc1.subscription.scheduler.PaymentScheduler;
import fr.remy.cc1.shared.domain.UserCreationStub;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;
import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.invoice.Invoices;
import fr.remy.cc1.shared.domain.mail.MockEmailSender;
import fr.remy.cc1.shared.domain.money.Money;
import fr.remy.cc1.subscription.domain.PaymentState;
import fr.remy.cc1.subscription.domain.creditcard.CreditCard;
import fr.remy.cc1.subscription.domain.creditcard.CreditCards;
import fr.remy.cc1.subscription.domain.currency.Currency;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccounts;
import fr.remy.cc1.member.domain.user.*;
import fr.remy.cc1.subscription.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.subscription.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.member.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.member.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.error.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentSchedulerTest {

    PaymentScheduler paymentScheduler;
    Users users;
    SubscriptionOffers subscriptionOffers;
    UserId myUserIdStub;
    Invoices invoices;
    CreditCards creditCards;
    PaypalAccounts paypalAccounts;
    UserId userId;
    SubscriptionOfferId subscriptionOfferId;

    @BeforeEach
    void initStubValues() {
        userId = UserId.of(1);
        subscriptionOfferId = SubscriptionOfferId.of(1);
        UsersData.setup(new ConcurrentHashMap<>());
        UserSubscriptionsData.setup(new ConcurrentHashMap<>());
        SubscriptionInvoiceData.setup(new ConcurrentHashMap<>());
        this.users = new InMemoryUsers(UsersData.getInstance().data, UserSubscriptionsData.getInstance().data);
        this.subscriptionOffers = new InMemorySubscriptionOffers(UsersData.getInstance().data, UserSubscriptionsData.getInstance().data, SubscriptionInvoiceData.getInstance().data);
        this.myUserIdStub = users.nextIdentity();
        this.invoices = new InMemoryInvoices(SubscriptionInvoiceData.getInstance().data);
        this.creditCards = new InMemoryCreditCards();

        UserCreationStub.initUserCreationTest(this.users, this.invoices);
        this.paymentScheduler = new PaymentScheduler(subscriptionOffers, creditCards, paypalAccounts, UserCreationEventBus.getInstance());
    }

    @Test
    @DisplayName("should try to pay for one user that have pay one mont ago")
    void userHasPaidSinceMoreThanOneMonth() throws ValidationException, NoSuchEntityException {
        User user = User.of(userId, "Machavoine", "R??my", new Email("pomme@pomme.fr"), new Password("aZertyu9?"), UserCategory.TRADESMAN);
        CreditCard creditCard = CreditCard.of(creditCards.nextIdentity(),"138416381", 1213, 321, "POMME", userId);
        this.users.save(user);
        this.creditCards.save(creditCard, userId);
        Money money = Money.of(new BigDecimal(10), Currency.EUR);
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(Money.of(new BigDecimal(10), Currency.EUR), 10, subscriptionOfferId, userId);
        this.users.saveSubscriptionOffer(userId, subscriptionOffer);
        Invoice invoice = Invoice.of(invoices.nextIdentity(), money,userId, PaymentState.VALIDATE, ZonedDateTime.now().minusMonths(1));
        this.invoices.saveSubscriptionInvoice(invoice, subscriptionOfferId);
        this.users.saveSubscriptionOffer(userId, subscriptionOffer);

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).size(), 1);
        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).get(0).getPaymentState(), PaymentState.VALIDATE);

        paymentScheduler.PayUserSubscriptionOffer();

        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).size(), 2);
        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).get(1).getPaymentState(), PaymentState.VALIDATE);
        assertEquals(MockEmailSender.getInstance().getCountMail(), 1);
    }

    @Test
    @DisplayName("should try to pay but the user have paid since less than one mont")
    void userHasPaidSinceLessThanOneMonth() throws ValidationException {
        User user = User.of(userId, "Machavoine", "R??my", new Email("pomme@pomme.fr"), new Password("aZertyu9?"), UserCategory.TRADESMAN);
        CreditCard creditCard = CreditCard.of(creditCards.nextIdentity(),"138416381", 1213, 321, "POMME", userId);
        this.users.save(user);
        this.creditCards.save(creditCard, userId);
        Money money = Money.of(new BigDecimal(10), Currency.EUR);
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(Money.of(new BigDecimal(10), Currency.EUR), 10, subscriptionOfferId, userId);
        this.users.saveSubscriptionOffer(userId, subscriptionOffer);
        Invoice invoice = Invoice.of(invoices.nextIdentity(), money,userId, PaymentState.VALIDATE, ZonedDateTime.now().minusDays(20));
        this.invoices.saveSubscriptionInvoice(invoice, subscriptionOfferId);
        this.users.saveSubscriptionOffer(userId, subscriptionOffer);

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).size(), 1);
        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).get(0).getPaymentState(), PaymentState.VALIDATE);

        paymentScheduler.PayUserSubscriptionOffer();

        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).size(), 1);
        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
    }

    @Test
    @DisplayName("should try to pay but the user have no valid invoice so not completely register")
    void userHaveNoValidInvoice() throws ValidationException {
        User user = User.of(userId, "Machavoine", "R??my", new Email("pomme@pomme.fr"), new Password("aZertyu9?"), UserCategory.TRADESMAN);
        CreditCard creditCard = CreditCard.of(creditCards.nextIdentity(),"138416381", 1213, 321, "POMME", userId);
        this.users.save(user);
        this.creditCards.save(creditCard, userId);
        Money money = Money.of(new BigDecimal(10), Currency.EUR);
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(Money.of(new BigDecimal(10), Currency.EUR), 10, subscriptionOfferId, userId);
        this.users.saveSubscriptionOffer(userId, subscriptionOffer);
        Invoice invoice = Invoice.of(invoices.nextIdentity(), money,userId, PaymentState.REJECTED, ZonedDateTime.now().minusMonths(1));
        this.invoices.saveSubscriptionInvoice(invoice, subscriptionOfferId);
        this.users.saveSubscriptionOffer(userId, subscriptionOffer);

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).size(), 1);
        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).get(0).getPaymentState(), PaymentState.REJECTED);

        paymentScheduler.PayUserSubscriptionOffer();

        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).size(), 1);
        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
    }

    @Test
    @DisplayName("should try to pay the user has valid Invoice since more than one month and rejected last")
    void userHaveNoValidInvoiceButAValidOneBefore() throws ValidationException {
        User user = User.of(userId, "Machavoine", "R??my", new Email("pomme@pomme.fr"), new Password("aZertyu9?"), UserCategory.TRADESMAN);
        CreditCard creditCard = CreditCard.of(creditCards.nextIdentity(),"138416381", 1213, 321, "POMME", userId);
        this.users.save(user);
        this.creditCards.save(creditCard, userId);
        Money money = Money.of(new BigDecimal(10), Currency.EUR);
        Invoice invoice = Invoice.of(invoices.nextIdentity(), money,userId, PaymentState.VALIDATE, ZonedDateTime.now().minusMonths(1).minusDays(1));
        Invoice invoice2 = Invoice.of(invoices.nextIdentity(), money,userId, PaymentState.REJECTED, ZonedDateTime.now().minusDays(1));
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(Money.of(new BigDecimal(10), Currency.EUR), 10, subscriptionOfferId, userId);
        this.users.saveSubscriptionOffer(userId, subscriptionOffer);
        this.invoices.saveSubscriptionInvoice(invoice, subscriptionOfferId);
        this.invoices.saveSubscriptionInvoice(invoice2, subscriptionOfferId);
        this.users.saveSubscriptionOffer(userId, subscriptionOffer);

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).size(), 2);
        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).get(1).getPaymentState(), PaymentState.REJECTED);

        paymentScheduler.PayUserSubscriptionOffer();

        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).size(), 3);
        assertEquals(this.invoices.findBySubscriptionOfferId(subscriptionOfferId).get(2).getPaymentState(), PaymentState.VALIDATE);
        assertEquals(MockEmailSender.getInstance().getCountMail(), 1);
    }

}

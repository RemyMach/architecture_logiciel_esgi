package fr.remy.cc1.application;

import fr.remy.cc1.application.scheduler.PaymentScheduler;
import fr.remy.cc1.domain.UserCreationStub;
import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.invoice.Invoice;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.payment.PaymentState;
import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.domain.payment.creditcard.CreditCards;
import fr.remy.cc1.domain.payment.currency.Currency;
import fr.remy.cc1.domain.payment.paypal.PaypalAccounts;
import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.error.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentSchedulerTest {

    PaymentScheduler paymentScheduler;
    Users users;
    UserId myUserIdStub;
    Invoices invoices;
    CreditCards creditCards;
    PaypalAccounts paypalAccounts;
    UserId userId;

    @BeforeEach
    void initStubValues() {
        userId = UserId.of(1);
        this.users = new InMemoryUsers();
        this.myUserIdStub = users.nextIdentity();
        this.invoices = new InMemoryInvoices();
        this.creditCards = new InMemoryCreditCards();

        UserCreationStub.initUserCreationTest(this.users, this.invoices);
        this.paymentScheduler = new PaymentScheduler(users, creditCards, paypalAccounts, UserCreationEventBus.getInstance());
    }

    @Test
    @DisplayName("should try to pay for two users")
    void userIsValid() throws ValidationException, NoSuchEntityException {
        User user = User.of(userId, "Machavoine", "Rémy", new Email("pomme@pomme.fr"), new Password("aZertyu9?"), UserCategory.TRADESMAN);
        CreditCard creditCard = CreditCard.of(creditCards.nextIdentity(),"138416381", 1213, 321, "POMME", userId);
        this.users.save(user);
        this.creditCards.save(creditCard, userId);
        Money money = Money.of(new BigDecimal(10), Currency.EUR);
        Invoice invoice = Invoice.of(invoices.nextIdentity(), money,userId, PaymentState.VALIDATE, ZonedDateTime.now().minusMonths(1));
        this.invoices.save(invoice);
        this.users.saveSubscriptionOffer(userId, SubscriptionOffer.of(Money.of(new BigDecimal(10), Currency.EUR), 10, List.of(invoice)));

        assertEquals(MockEmailSender.getInstance().getCountMail(), 0);
        assertEquals(this.users.findAll().size(), 0);

        paymentScheduler.PayUserSubscriptionOffer();

        assertEquals(this.users.findAll().size(), 1);
        assertEquals(this.users.byId(userId).getEmail().email, emailStub);
        assertEquals(MockEmailSender.getInstance().getCountMail(), 1);
    }

}

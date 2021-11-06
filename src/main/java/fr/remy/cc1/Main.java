package fr.remy.cc1;

import fr.remy.cc1.domain.*;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.Mail;
import fr.remy.cc1.domain.payment.Invoices;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.UserService;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.*;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.creditcards.SaveCreditCardEvent;
import fr.remy.cc1.infrastructure.creditcards.SaveCreditCardEventSubscription;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.invoices.SubscriptionSuccessfulEventInvoiceSubscription;
import fr.remy.cc1.infrastructure.mail.RegisterUserEventMessengerSubscription;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.infrastructure.mail.SubscriptionSuccessfulEventMessengerSubscription;
import fr.remy.cc1.infrastructure.payment.*;
import fr.remy.cc1.infrastructure.users.InMemoryUsers;
import fr.remy.cc1.infrastructure.users.SubscriptionSuccessfulEventUserSubscription;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Mail sandboxMail = new SandboxMail();
        Invoices invoices = new InMemoryInvoices();
        Users users = new InMemoryUsers();
        CreditCards creditCards = new InMemoryCreditCards();

        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(sandboxMail),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices),
                new SubscriptionSuccessfulEventUserSubscription(users)
        );

        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(sandboxMail)),
                SaveCreditCardEvent.class, Collections.singletonList(new SaveCreditCardEventSubscription(creditCards)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions)
        );

        final UserId myUserId = users.nextIdentity();
        User user = User.of(myUserId, "Machavoine", "Rémy", "pomme@pomme.fr", "aZertyu9!");
        CreditCard creditCard = CreditCard.of(1234567262, 1203, 321, "POMME");

        Payment payment = new CreditCardPayment(creditCard);

        EventBus eventBus = new DefaultEventBus(subscriptionMap);
        UserService userService = new UserService(users, eventBus);
        PaymentService paymentService = new PaymentService(payment, eventBus);
        CreditCardService creditCardService = new CreditCardService(eventBus);

        createUser(userService, user, paymentService);
        creditCardService.save(creditCard, user);
    }

    private static void createUser(UserService userService, User user, PaymentService paymentService) {
        userService.create(user);
        paySubscriptionOffer(user, paymentService);
    }

    private static void paySubscriptionOffer(User user, PaymentService paymentService) {
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(12.05),10);
        paymentService.paySubscription(subscriptionOffer,  Currency.EUR, user);
    }
}

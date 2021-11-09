package fr.remy.cc1.domain;

import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.payment.Invoices;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.RegisterUserEvent;
import fr.remy.cc1.infrastructure.SubscriptionSuccessfulEvent;
import fr.remy.cc1.infrastructure.UserCreationEventBus;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.creditcards.SaveCreditCardEvent;
import fr.remy.cc1.infrastructure.creditcards.SaveCreditCardEventSubscription;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.invoices.SubscriptionSuccessfulEventInvoiceSubscription;
import fr.remy.cc1.infrastructure.mail.EmailSender;
import fr.remy.cc1.infrastructure.mail.RegisterUserEventMessengerSubscription;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.infrastructure.mail.SubscriptionSuccessfulEventMessengerSubscription;
import fr.remy.cc1.infrastructure.users.InMemoryUsers;
import fr.remy.cc1.infrastructure.users.SubscriptionSuccessfulEventUserSubscription;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserCreationStub {

    public static void initUserCreationTest() {
        buildStubMailSender();
        buildStubEventBus();
    }

    private static void buildStubMailSender() {
        EmailSender emailSender = EmailSender.getInstance();
        emailSender.setMail(new SandboxMail());
    }

    private static void buildStubEventBus() {
        Users users = new InMemoryUsers();
        Invoices invoices = new InMemoryInvoices();
        CreditCards creditCards = new InMemoryCreditCards();
        EmailSender emailSender = EmailSender.getInstance();

        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(emailSender),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices),
                new SubscriptionSuccessfulEventUserSubscription(users)
        );

        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(emailSender)),
                SaveCreditCardEvent.class, Collections.singletonList(new SaveCreditCardEventSubscription(creditCards)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions)
        );

        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);
    }
}

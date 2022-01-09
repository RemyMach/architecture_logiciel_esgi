package fr.remy.cc1.domain;

import fr.remy.cc1.application.customer.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.application.invoice.SubscriptionPaymentFailedEventInvoiceSubscription;
import fr.remy.cc1.application.mail.SubscriptionPaymentFailedEventMessengerSubscription;
import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.application.user.RegisterUserEvent;
import fr.remy.cc1.application.customer.SubscriptionSuccessfulEvent;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.application.invoice.SubscriptionSuccessfulEventInvoiceSubscription;
import fr.remy.cc1.application.mail.RegisterUserEventMessengerSubscription;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.application.mail.SubscriptionSuccessfulEventMessengerSubscription;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserCreationStub {

    public static void initUserCreationTest(Users users, Invoices invoices) {
        buildStubMailSender();
        buildStubEventBus(users, invoices);
    }

    private static void buildStubMailSender() {
        MockEmailSender.getInstance().resetMockEmailSenderInstance();
        MockEmailSender mockEmailSender = MockEmailSender.getInstance();
        mockEmailSender.setMail(new SandboxMail());
    }

    private static void buildStubEventBus(Users users, Invoices invoices) {
        MockEmailSender mockEmailSender = MockEmailSender.getInstance();

        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(mockEmailSender),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices, users)
        );

        List<Subscriber> subscriptionPaymentFailedEventSubscriptions = Arrays.asList(
                new SubscriptionPaymentFailedEventMessengerSubscription(mockEmailSender),
                new SubscriptionPaymentFailedEventInvoiceSubscription(invoices, users)
        );

        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(mockEmailSender)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions),
                SubscriptionPaymentFailedEvent.class, Collections.unmodifiableList(subscriptionPaymentFailedEventSubscriptions)
        );

        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);
    }
}

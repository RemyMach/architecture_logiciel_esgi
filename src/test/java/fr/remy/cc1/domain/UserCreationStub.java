package fr.remy.cc1.domain;

import fr.remy.cc1.application.customer.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.application.invoice.SubscriptionPaymentFailedEventInvoiceSubscription;
import fr.remy.cc1.application.mail.SubscriptionPaymentFailedEventMessengerSubscription;
import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.application.user.RegisteredUserEvent;
import fr.remy.cc1.application.customer.SubscriptionSuccessTerminatedEvent;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.application.invoice.SubscriptionSuccessTerminatedEventInvoiceSubscription;
import fr.remy.cc1.application.mail.RegisteredUserEventMessengerSubscription;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.application.mail.SubscriptionSuccessTerminatedEventMessengerSubscription;

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
                new SubscriptionSuccessTerminatedEventMessengerSubscription(mockEmailSender),
                new SubscriptionSuccessTerminatedEventInvoiceSubscription(invoices, users)
        );

        List<Subscriber> subscriptionPaymentFailedEventSubscriptions = Arrays.asList(
                new SubscriptionPaymentFailedEventMessengerSubscription(mockEmailSender),
                new SubscriptionPaymentFailedEventInvoiceSubscription(invoices, users)
        );

        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisteredUserEvent.class, Collections.singletonList(new RegisteredUserEventMessengerSubscription(mockEmailSender)),
                SubscriptionSuccessTerminatedEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions),
                SubscriptionPaymentFailedEvent.class, Collections.unmodifiableList(subscriptionPaymentFailedEventSubscriptions)
        );

        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);
    }
}

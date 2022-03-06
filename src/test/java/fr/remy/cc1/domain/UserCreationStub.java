package fr.remy.cc1.domain;

import fr.remy.cc1.member.application.*;
import fr.remy.cc1.subscription.application.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.subscription.application.SubscriptionPaymentFailedEventMessengerSubscription;
import fr.remy.cc1.subscription.application.SubscriptionPaymentSuccessTerminatedEvent;
import fr.remy.cc1.subscription.application.SubscriptionPaymentSuccessTerminatedEventMessengerSubscription;
import fr.remy.cc1.subscription.application.invoice.SubscriptionPaymentFailedEventInvoiceSubscription;
import fr.remy.cc1.subscription.application.invoice.SubscriptionSuccessTerminatedEventInvoiceSubscription;
import fr.remy.cc1.subscription.domain.invoice.Invoices;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.member.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.event.Subscriber;

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
                new SubscriptionPaymentSuccessTerminatedEventMessengerSubscription(mockEmailSender),
                new SubscriptionSuccessTerminatedEventInvoiceSubscription(invoices, users)
        );

        List<Subscriber> subscriptionPaymentFailedEventSubscriptions = Arrays.asList(
                new SubscriptionPaymentFailedEventMessengerSubscription(mockEmailSender),
                new SubscriptionPaymentFailedEventInvoiceSubscription(invoices, users)
        );

        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisteredContractorEvent.class, Collections.singletonList(new RegisteredContractorEventMessengerSubscription(mockEmailSender)),
                RegisteredTradesmanEvent.class, Collections.singletonList(new RegisteredTradesmanEventMessengerSubscription(mockEmailSender)),
                SubscriptionPaymentSuccessTerminatedEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions),
                SubscriptionPaymentFailedEvent.class, Collections.unmodifiableList(subscriptionPaymentFailedEventSubscriptions)
        );

        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);
    }
}

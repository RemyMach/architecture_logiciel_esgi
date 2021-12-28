package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.application.CreateSubscriptionOfferCommandHandler;
import fr.remy.cc1.application.CreateUserCommandHandler;
import fr.remy.cc1.domain.customer.SubscriptionSuccessfulEvent;
import fr.remy.cc1.domain.customer.SubscriptionSuccessfulEventCustomerSubscription;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.invoice.SubscriptionSuccessfulEventInvoiceSubscription;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.domain.mail.RegisterUserEventMessengerSubscription;
import fr.remy.cc1.domain.mail.SubscriptionSuccessfulEventMessengerSubscription;
import fr.remy.cc1.domain.payment.creditcard.CreditCards;
import fr.remy.cc1.domain.payment.creditcard.SaveCreditCardEvent;
import fr.remy.cc1.domain.payment.creditcard.SaveCreditCardEventSubscription;
import fr.remy.cc1.domain.user.RegisterUserEvent;
import fr.remy.cc1.domain.user.UserService;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.kernel.event.Subscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class UserConfiguration {

    @Bean
    public Users users() {
        return new InMemoryUsers();
    }

    @Bean
    public Invoices invoices() {
        return new InMemoryInvoices();
    }

    @Bean
    public CreditCards creditCards() {
        return new InMemoryCreditCards();
    }

    @Bean
    public EventBus<Event> userCreationEventBus() {

        EmailSender emailSender = EmailSender.getInstance();
        emailSender.setMail(new SandboxMail());

        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(emailSender),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices()),
                new SubscriptionSuccessfulEventCustomerSubscription(users())
        );

        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(emailSender)),
                SaveCreditCardEvent.class, Collections.singletonList(new SaveCreditCardEventSubscription(creditCards())),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions)
        );

        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);

        return userCreationEventBus;
    }

    @Bean
    public UserService userService() {
        return new UserService(users());
    }

    @Bean
    public CreateUserCommandHandler createUserCommandHandler() {
        return new CreateUserCommandHandler(users(), userService(), userCreationEventBus());
    }

    @Bean
    public CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler() {
        return new CreateSubscriptionOfferCommandHandler(creditCards(),userService(), userCreationEventBus());
    }

}

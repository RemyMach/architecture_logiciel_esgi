package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.application.CreatePaymentCommandHandler;
import fr.remy.cc1.application.CreateSubscriptionOfferCommandHandler;
import fr.remy.cc1.application.CreateUserCommandHandler;
import fr.remy.cc1.application.customer.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.application.customer.SubscriptionSuccessTerminatedEvent;
import fr.remy.cc1.application.invoice.SubscriptionPaymentFailedEventInvoiceSubscription;
import fr.remy.cc1.application.invoice.SubscriptionSuccessTerminatedEventInvoiceSubscription;
import fr.remy.cc1.application.mail.RegisteredUserEventMessengerSubscription;
import fr.remy.cc1.application.mail.SubscriptionPaymentFailedEventMessengerSubscription;
import fr.remy.cc1.application.mail.SubscriptionSuccessTerminatedEventMessengerSubscription;
import fr.remy.cc1.application.scheduler.PaymentScheduler;
import fr.remy.cc1.application.user.RegisteredUserEvent;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.domain.payment.creditcard.CreditCards;
import fr.remy.cc1.domain.payment.paypal.PaypalAccounts;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.exposition.authentication.AuthMiddleware;
import fr.remy.cc1.exposition.authentication.CreateAuthTokenCommandHandler;
import fr.remy.cc1.exposition.authentication.Tokens;
import fr.remy.cc1.infrastructure.authentication.token.InMemoryToken;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.infrastructure.paypalAccounts.InMemoryPaypalAccounts;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.kernel.event.Subscriber;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
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
                new SubscriptionSuccessTerminatedEventMessengerSubscription(emailSender),
                new SubscriptionSuccessTerminatedEventInvoiceSubscription(invoices(), users())
        );

        List<Subscriber> subscriptionPaymentFailedEventSubscriptions = Arrays.asList(
                new SubscriptionPaymentFailedEventMessengerSubscription(emailSender),
                new SubscriptionPaymentFailedEventInvoiceSubscription(invoices(), users())
        );

        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisteredUserEvent.class, Collections.singletonList(new RegisteredUserEventMessengerSubscription(emailSender)),
                SubscriptionSuccessTerminatedEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions),
                SubscriptionPaymentFailedEvent.class, Collections.unmodifiableList(subscriptionPaymentFailedEventSubscriptions)
        );

        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);

        return userCreationEventBus;
    }

    @Bean
    public PaypalAccounts paypalAccounts() {
        return new InMemoryPaypalAccounts();
    }

    @Bean
    public CreateUserCommandHandler createUserCommandHandler() {
        return new CreateUserCommandHandler(users(), userCreationEventBus());
    }

    @Bean
    public CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler() {
        return new CreateSubscriptionOfferCommandHandler(creditCards(), paypalAccounts(), users(), userCreationEventBus());
    }

    @Bean
    public CreatePaymentCommandHandler createPaymentCommandHandler() {
        return new CreatePaymentCommandHandler(creditCards(), paypalAccounts(), users());
    }

    @Bean
    PaymentScheduler paymentScheduler() {
        return new PaymentScheduler(users(), creditCards(), paypalAccounts(), userCreationEventBus());
    }

    @Bean
    public Tokens tokens() {
        return new InMemoryToken();
    }

    @Bean
    public CreateAuthTokenCommandHandler createAuthTokenCommandHandler() {
        return new CreateAuthTokenCommandHandler(tokens());
    }


    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(authenticationFilter());
        registration.addUrlPatterns("/subscriptions", "/certificates/*");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public AuthMiddleware authenticationFilter() {
        return new AuthMiddleware(tokens());
    }



}

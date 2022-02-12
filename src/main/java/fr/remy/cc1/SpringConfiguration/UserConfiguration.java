package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.application.mail.*;
import fr.remy.cc1.member.data.ContractorsData;
import fr.remy.cc1.member.data.TradesmansData;
import fr.remy.cc1.member.data.UsersData;
import fr.remy.cc1.subscription.application.payment.CreatePaymentCommandHandler;
import fr.remy.cc1.subscription.application.CreateSubscriptionOfferCommandHandler;
import fr.remy.cc1.subscription.application.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.subscription.application.SubscriptionSuccessTerminatedEvent;
import fr.remy.cc1.subscription.application.invoice.SubscriptionPaymentFailedEventInvoiceSubscription;
import fr.remy.cc1.subscription.application.invoice.SubscriptionSuccessTerminatedEventInvoiceSubscription;
import fr.remy.cc1.subscription.scheduler.PaymentScheduler;
import fr.remy.cc1.subscription.domain.invoice.Invoices;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.subscription.domain.creditcard.CreditCards;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccounts;
import fr.remy.cc1.member.domain.user.Tradesman.Tradesmans;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.member.domain.user.contractor.Contractors;
import fr.remy.cc1.exposition.authentication.AuthMiddleware;
import fr.remy.cc1.exposition.authentication.CreateAuthTokenCommandHandler;
import fr.remy.cc1.exposition.authentication.Tokens;
import fr.remy.cc1.infrastructure.authentication.token.InMemoryToken;
import fr.remy.cc1.member.infrastructure.contractor.InMemoryContractors;
import fr.remy.cc1.subscription.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.subscription.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.subscription.infrastructure.paypalAccounts.InMemoryPaypalAccounts;
import fr.remy.cc1.member.infrastructure.tradesman.InMemoryTradesmans;
import fr.remy.cc1.member.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.member.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.member.application.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableScheduling
public class UserConfiguration {

    @Bean
    public ContractorsData contractorsData() {
        ContractorsData.setup(new ConcurrentHashMap<>());
        return ContractorsData.getInstance();
    }

    @Bean
    public UsersData usersData() {
        UsersData.setup(new ConcurrentHashMap<>());
        return UsersData.getInstance();
    }

    @Bean
    public TradesmansData tradesmansData() {
        TradesmansData.setup(new ConcurrentHashMap<>());
        return TradesmansData.getInstance();
    }

    @Bean
    public Users users() {
        return new InMemoryUsers(usersData().data);
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
                RegisterContractorEvent.class, Collections.singletonList(new RegisteredContractorEventMessengerSubscription(emailSender)),
                RegisterTradesmanEvent.class, Collections.singletonList(new RegisteredTradesmanEventMessengerSubscription(emailSender)),
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

    @Bean
    public Tradesmans tradesmans() {
        return new InMemoryTradesmans(tradesmansData().data);
    }

    @Bean
    public Contractors contractors() {
        return new InMemoryContractors(contractorsData().data);
    }

    @Bean
    public CreateTradesmanCommandHandler createTradesmanCommandHandler() {
        return new CreateTradesmanCommandHandler(users(), tradesmans(), userCreationEventBus());
    }

    @Bean
    public CreateContractorCommandHandler createContractorCommandHandler() {
        return new CreateContractorCommandHandler(users(), contractors(), userCreationEventBus());
    }

}

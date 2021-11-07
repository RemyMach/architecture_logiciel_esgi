package fr.remy.cc1;

import fr.remy.cc1.domain.*;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.Mail;
import fr.remy.cc1.domain.payment.CreditCardId;
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

        String lastnameStub = "Machavoine";
        String firstnameStub = "Rémy";
        String emailStub = "pomme@pomme.fr";
        String passwordStub = "aZertyu9?";

        BigDecimal priceSubscriptionOfferStub = new BigDecimal("12.05");
        int discountPercentageStub = 10;

        String currencyChoiceStub = "EUR";
        String paymentChoiceStub = "CreditCard";
        boolean saveCreditCardStub = true;

        Users users = new InMemoryUsers();
        Mail mail = new SandboxMail();
        Invoices invoices = new InMemoryInvoices();
        CreditCards creditCards = new InMemoryCreditCards();

        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(priceSubscriptionOfferStub,discountPercentageStub);

        final UserId myUserId = users.nextIdentity();
        User user = User.of(myUserId, lastnameStub, firstnameStub, emailStub, passwordStub);

        validCurrency(currencyChoiceStub);

        final CreditCardId creditCardId = creditCards.nextIdentity();
        CreditCard creditCard = CreditCard.of(creditCardId,1234567262, 1203, 321, "POMME");

        Map<Class, List<Subscriber>> subscriptionMap = initSubscriptionMap(mail, invoices, users, creditCards);
        createACompleteUser(user, users, subscriptionMap, creditCard, currencyChoiceStub, paymentChoiceStub, saveCreditCardStub, subscriptionOffer);
    }

    private static void createACompleteUser(
            User user,
            Users users,
            Map<Class, List<Subscriber>> subscriptionMap,
            CreditCard creditCard, String currency,
            String paymentMethod,
            boolean saveCreditCard,
            SubscriptionOffer subscriptionOffer
    ) {

        Payment payment = getPayment(paymentMethod, creditCard);

        EventBus eventBus = new DefaultEventBus(subscriptionMap);
        UserService userService = new UserService(users, eventBus);
        PaymentService paymentService = new PaymentService(payment, eventBus);
        CreditCardService creditCardService = new CreditCardService(eventBus);

        paymentService.paySubscription(subscriptionOffer,  Currency.valueOf(currency), user);
        userService.create(user);
        if(saveCreditCard)
            creditCardService.save(creditCard, user);
    }


    private static Payment getPayment(String choice, CreditCard creditCard) {
        if(choice.equals("Paypal")) {
            return new PaypalPayment();
        }else if (choice.equals("CreditCard")) {
            return new CreditCardPayment(creditCard);
        }
        throw new UnsupportedOperationException("You can choose uniquely Paypal and CreditCard to pay");
    }

    private static void validCurrency(String currencyChoice) {
        try {
            Currency.valueOf(currencyChoice);
        }catch(IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException("EUR and USD are available to pay");
        }
    }

    private static Map<Class, List<Subscriber>> initSubscriptionMap(Mail mail, Invoices invoices, Users users, CreditCards creditCards) {
        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(mail),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices),
                new SubscriptionSuccessfulEventUserSubscription(users)
        );

        return Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(mail)),
                SaveCreditCardEvent.class, Collections.singletonList(new SaveCreditCardEventSubscription(creditCards)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions)
        );
    }
}

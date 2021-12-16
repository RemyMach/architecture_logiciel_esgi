package fr.remy.cc1;

import fr.remy.cc1.domain.customer.*;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.payment.*;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.domain.invoice.SubscriptionSuccessfulEventInvoiceSubscription;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.domain.mail.RegisterUserEventMessengerSubscription;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.domain.mail.SubscriptionSuccessfulEventMessengerSubscription;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        EmailSender emailSender = EmailSender.getInstance();
        emailSender.setMail(new SandboxMail());

        Users users = new InMemoryUsers();
        Invoices invoices = new InMemoryInvoices();
        CreditCards creditCards = new InMemoryCreditCards();

        Map<Class, List<Subscriber>> subscriptionMap = initSubscriptionMap(emailSender, invoices, users, creditCards);
        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);


        String lastnameStub = "Machavoine";
        String firstnameStub = "Rémy";
        String emailStub = "pomme@pomme.fr";
        String passwordStub = "aZertyu9?";

        BigDecimal priceSubscriptionOfferStub = new BigDecimal("12.05");
        int discountPercentageStub = 10;

        String currencyChoiceStub = "EUR";
        String paymentChoiceStub = "CreditCard";
        String userCategoryChoiceStub = "Trademan";
        UserCategoryCreator userCategoryCreator = new UserCategoryCreator();
        boolean saveCreditCardStub = true;

        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(priceSubscriptionOfferStub,discountPercentageStub);

        final UserId myUserId = users.nextIdentity();
        User user = User.of(myUserId, lastnameStub, firstnameStub, emailStub, passwordStub, userCategoryCreator.getValueOf(userCategoryChoiceStub));

        CurrencyValidator.getInstance().test(currencyChoiceStub);

        final CreditCardId creditCardId = creditCards.nextIdentity();
        CreditCard creditCard = CreditCard.of(creditCardId,1234567262, 1203, 321, "POMME");
        Payer payer = new Payer(creditCard, null);

        createUser(user, users, payer, currencyChoiceStub, paymentChoiceStub, saveCreditCardStub, subscriptionOffer);
    }

    private static void createUser(
            User user,
            Users users,
            Payer payer,
            String currency,
            String paymentMethod,
            boolean saveCreditCard,
            SubscriptionOffer subscriptionOffer
    ) {
        Payment payment = getPayment(paymentMethod, payer);

        UserService userService = new UserService(users);
        PaymentService paymentService = new PaymentService(payment);

        paymentService.paySubscription(subscriptionOffer,  Currency.valueOf(currency), user);
        userService.create(user);
        if(saveCreditCard)
            UserCreationEventBus.getInstance().send(SaveCreditCardEvent.of(payer.getCreditCard(), user));
    }

    private static Map<Class, List<Subscriber>> initSubscriptionMap(EmailSender emailSender, Invoices invoices, Users users, CreditCards creditCards) {
        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(emailSender),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices),
                new SubscriptionSuccessfulEventCustomerSubscription(users)
        );

        return Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(emailSender)),
                SaveCreditCardEvent.class, Collections.singletonList(new SaveCreditCardEventSubscription(creditCards)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions)
        );
    }

    private static Payment getPayment(String paymentMethod, Payer payer) {
        if(!PaymentMethodValidator.getInstance().test(paymentMethod)) {
            throw new IllegalArgumentException(PaymentMethodValidator.exceptionMessage);
        }
        if(paymentMethod.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(0))) {
            return PaymentCreator.withPaypal(payer.getPaypalAccount());
        }else if(paymentMethod.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(1))) {
            return PaymentCreator.withCreditCard(payer.getCreditCard(), PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                    List.of(new CreditCardChecker(), new CreditCardApproveTradesman(), new CreditCardContractor())
            ));
        }

        throw new IllegalArgumentException(PaymentMethodValidator.exceptionMessage);
    }
}

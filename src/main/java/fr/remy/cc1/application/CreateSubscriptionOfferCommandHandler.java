package fr.remy.cc1.application;

import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.payment.*;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.domain.payment.paypal.PaypalAccounts;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.UserService;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

import java.util.List;

public class CreateSubscriptionOfferCommandHandler implements CommandHandler<CreateSubscriptionOffer, Void> {

    private final CreditCards creditCards;
    private final PaypalAccounts paypalAccounts;
    private final UserService userService;
    private final EventBus<Event> eventBus;

    public CreateSubscriptionOfferCommandHandler(CreditCards creditCards, PaypalAccounts paypalAccounts, UserService userService, EventBus<Event> eventBus) {
        this.creditCards = creditCards;
        this.paypalAccounts = paypalAccounts;
        this.userService = userService;
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(CreateSubscriptionOffer command) {
        User user = this.userService.getUser(UserId.of(command.userId));

        if(!PaymentMethodValidator.getInstance().test(command.payment)) {
            throw new IllegalArgumentException(PaymentMethodValidator.exceptionMessage);
        }
        Payment payment = null;
        if(command.payment.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(0))) {
            payment = PaymentDirector.createPaypalPayment(paypalAccounts.byUserId(user.getUserId()));
        }else if(command.payment.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(1))) {
            payment = PaymentDirector.createCreditCardPayment(creditCards.byUserId(user.getUserId()), PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                    List.of(new CreditCardChecker(), new CreditCardApproveTradesman(), new CreditCardContractor()))
            );
        }
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(command.amount, command.discountPercentage);
        CurrencyValidator.getInstance().test(command.currency);

        PaymentService paymentService = new PaymentService(payment);
        paymentService.paySubscription(subscriptionOffer,  Currency.valueOf(command.currency), user);
        this.userService.create(user);

        return null;
    }
}

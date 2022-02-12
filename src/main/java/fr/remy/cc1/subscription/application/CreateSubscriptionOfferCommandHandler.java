package fr.remy.cc1.subscription.application;

import fr.remy.cc1.subscription.application.payment.PaymentService;
import fr.remy.cc1.subscription.domain.SubscriptionOffers;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;
import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.Money;
import fr.remy.cc1.subscription.domain.Payment;
import fr.remy.cc1.subscription.domain.PaymentDirector;
import fr.remy.cc1.subscription.domain.PaymentMethod.PaymentMethod;
import fr.remy.cc1.subscription.domain.PaymentMethod.PaymentMethodCreator;
import fr.remy.cc1.subscription.domain.creditcard.*;
import fr.remy.cc1.subscription.domain.currency.CurrencyCreator;
import fr.remy.cc1.subscription.domain.currency.CurrencyValidator;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccounts;
import fr.remy.cc1.domain.User;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CreateSubscriptionOfferCommandHandler implements CommandHandler<CreateSubscriptionOffer, Void> {

    private final CreditCards creditCards;
    private final PaypalAccounts paypalAccounts;
    private final SubscriptionOffers subscriptionOffers;
    private final Users users;
    private final EventBus<Event> eventBus;

    public CreateSubscriptionOfferCommandHandler(CreditCards creditCards, PaypalAccounts paypalAccounts, SubscriptionOffers subscriptionOffers, Users users, EventBus<Event> eventBus) {
        this.creditCards = creditCards;
        this.paypalAccounts = paypalAccounts;
        this.users = users;
        this.eventBus = eventBus;
        this.subscriptionOffers = subscriptionOffers;
    }

    @Override
    public Void handle(CreateSubscriptionOffer command) throws Exception {
        User user = this.users.byId(command.userId);

        PaymentMethod paymentMethodEnum = PaymentMethodCreator.getValueOf(command.payment);
        Payment payment = null;
        if(paymentMethodEnum == PaymentMethod.Paypal) {
            payment = PaymentDirector.createPaypalPayment(paypalAccounts.findByUserId(user.getUserId()));
        }else if(paymentMethodEnum == PaymentMethod. CreditCard) {
            payment = PaymentDirector.createCreditCardPayment(creditCards.findByUserId(user.getUserId()), PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                    List.of(new CreditCardValidityMiddleware(), new CreditCardValidityTradeMiddleware(), new CreditCardBankAccountValidityMiddleware()))
            );
        }
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new Money(command.amount, CurrencyCreator.getValueOf(command.currency)), command.discountPercentage, this.subscriptionOffers.nextIdentity(), user.getUserId());
        PaymentService paymentService = new PaymentService(payment, this.eventBus);
        paymentService.paySubscription(subscriptionOffer, user);
        return null;
    }
}

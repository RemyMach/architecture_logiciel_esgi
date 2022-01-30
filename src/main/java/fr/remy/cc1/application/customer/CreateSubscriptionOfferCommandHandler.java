package fr.remy.cc1.application.customer;

import fr.remy.cc1.application.payment.PaymentService;
import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.invoice.Invoice;
import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.payment.PaymentDirector;
import fr.remy.cc1.domain.payment.PaymentMethod.PaymentMethod;
import fr.remy.cc1.domain.payment.PaymentMethod.PaymentMethodCreator;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.payment.currency.CurrencyCreator;
import fr.remy.cc1.domain.payment.currency.CurrencyValidator;
import fr.remy.cc1.domain.payment.paypal.PaypalAccounts;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CreateSubscriptionOfferCommandHandler implements CommandHandler<CreateSubscriptionOffer, Void> {

    private final CreditCards creditCards;
    private final PaypalAccounts paypalAccounts;
    private final Users users;
    private final EventBus<Event> eventBus;

    public CreateSubscriptionOfferCommandHandler(CreditCards creditCards, PaypalAccounts paypalAccounts, Users users, EventBus<Event> eventBus) {
        this.creditCards = creditCards;
        this.paypalAccounts = paypalAccounts;
        this.users = users;
        this.eventBus = eventBus;
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
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new Money(command.amount, CurrencyCreator.getValueOf(command.currency)), command.discountPercentage, new ArrayList<Invoice>());
        CurrencyValidator.getInstance().test(command.currency);

        PaymentService paymentService = new PaymentService(payment, this.eventBus);
        paymentService.paySubscription(subscriptionOffer, user);
        return null;
    }
}

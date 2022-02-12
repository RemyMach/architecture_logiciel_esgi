package fr.remy.cc1.subscription.scheduler;

import fr.remy.cc1.subscription.application.payment.PaymentService;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;
import fr.remy.cc1.subscription.domain.Payment;
import fr.remy.cc1.subscription.domain.PaymentDirector;
import fr.remy.cc1.subscription.domain.creditcard.*;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccount;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccounts;
import fr.remy.cc1.domain.User;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentScheduler {

    private final Users users;
    private final CreditCards creditCards;
    private final PaypalAccounts paypalAccounts;
    private final EventBus<Event> eventBus;

    @Autowired
    public PaymentScheduler(Users users, CreditCards creditCards, PaypalAccounts paypalAccounts, EventBus<Event> eventBus) {
        this.users = users;
        this.creditCards = creditCards;
        this.paypalAccounts = paypalAccounts;
        this.eventBus = eventBus;
    }

    @Scheduled(cron = "* * 18 * * *")
    public void PayUserSubscriptionOffer() {
        List<User> userList = users.findAllByPaidSinceMoreThanCertainMonthAgo(1).stream().collect(Collectors.toList());


        for(User user: userList) {
            this.payUserSubscription(user);
        }
    }

    private void payUserSubscription(User user) {
        SubscriptionOffer subscriptionOffer = users.getSubscriptionOffer(user.getUserId());
        Payment payment = null;
        CreditCard creditCard = this.creditCards.findByUserId(user.getUserId());
        if(creditCard != null) {
            payment = PaymentDirector.createCreditCardPayment(creditCard, PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                    List.of(new CreditCardValidityMiddleware(), new CreditCardValidityTradeMiddleware(), new CreditCardBankAccountValidityMiddleware()))
            );
        }else {
            PaypalAccount paypalAccount = this.paypalAccounts.findByUserId(user.getUserId());
            if(paypalAccount != null) {
                payment = PaymentDirector.createPaypalPayment(paypalAccounts.findByUserId(user.getUserId()));
            }
        }
        assert payment != null;
        PaymentService paymentService = new PaymentService(payment, this.eventBus);
        try {
            paymentService.paySubscription(subscriptionOffer, user);
            System.out.println("félicitations vous avez payé");
        }catch (PaymentProcessValidationException paymentProcessValidationException){
            System.out.println("Le paiement est un echec");
        }
    }
}

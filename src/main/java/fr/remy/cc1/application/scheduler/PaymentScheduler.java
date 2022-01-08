package fr.remy.cc1.application.scheduler;

import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.payment.Card;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.payment.PaymentDirector;
import fr.remy.cc1.domain.payment.PaymentService;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.domain.payment.paypal.PaypalAccounts;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentScheduler {

    private final Users users;
    private final CreditCards creditCards;
    private final PaypalAccounts paypalAccounts;


    @Autowired
    public PaymentScheduler(Users users, CreditCards creditCards, PaypalAccounts paypalAccounts) {
        this.users = users;
        this.creditCards = creditCards;
        this.paypalAccounts = paypalAccounts;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void PayUserSubscriptionOffer() throws PaymentProcessValidationException {
        List<User> userList = users.findAllByPaidSinceMoreThanCertainMonthAgo(0);

        System.out.println(userList);
        for(User user: userList) {
            this.payUserSubscription(user);
        }

        //TODO récupérer le dernier paiement
        // si il date de moins d'un mois alors il n'a pas à payer
        // essayer le paiement 10 fois, si fonctionne pas envoi de mail suivant l'étape pour dire erreur de paiement
        // vérifier vos informations de carte de crédit
    }

    private void payUserSubscription(User user) throws PaymentProcessValidationException {
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
        PaymentService paymentService = new PaymentService(payment);
        paymentService.paySubscription(subscriptionOffer, user);
    }
}

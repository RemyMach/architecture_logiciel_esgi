package fr.remy.cc1.infrastructure;

import fr.remy.cc1.domain.Handler;
import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.UserMailSender;
import fr.remy.cc1.domain.payment.ApproveTradesman;
import fr.remy.cc1.domain.payment.Contractor;
import fr.remy.cc1.domain.payment.PaymentProcess;

public class PaySubscriptionEventSubscription implements Subscriber<PaySubscriptionEvent> {

    public PaySubscriptionEventSubscription() {

    }

    @Override
    public void accept(PaySubscriptionEvent paySubscriptionEvent) {
        //User user = paySubscriptionEvent.getUser();
        Handler approveTradesman = new ApproveTradesman();
        Handler contractor = new Contractor();
        Handler paymentProcess = new PaymentProcess();
        paymentProcess.setNext(approveTradesman);
        approveTradesman.setNext(contractor);

        paymentProcess.process();
    }
}

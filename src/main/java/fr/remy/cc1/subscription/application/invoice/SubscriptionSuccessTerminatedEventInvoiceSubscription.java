package fr.remy.cc1.subscription.application.invoice;

import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.subscription.application.SubscriptionPaymentSuccessTerminatedEvent;
import fr.remy.cc1.subscription.domain.PaymentState;
import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.invoice.InvoiceId;
import fr.remy.cc1.subscription.domain.invoice.Invoices;

import java.time.ZonedDateTime;

public class SubscriptionSuccessTerminatedEventInvoiceSubscription implements Subscriber<SubscriptionPaymentSuccessTerminatedEvent> {

    private final Invoices invoices;
    private final Users users;

    public SubscriptionSuccessTerminatedEventInvoiceSubscription(Invoices invoices, Users users) {
        this.invoices = invoices;
        this.users = users;
    }

    //TODO voir ce que ça donne si je passe deux subscriptionOffer pour le même user à la suite
    @Override
    public void accept(SubscriptionPaymentSuccessTerminatedEvent subscriptionPaymentSuccessTerminatedEvent) {
        final InvoiceId invoiceId = this.invoices.nextIdentity();
        Invoice invoice = Invoice.of(invoiceId, subscriptionPaymentSuccessTerminatedEvent.getMoney(), subscriptionPaymentSuccessTerminatedEvent.getUserId(), PaymentState.VALIDATE, ZonedDateTime.now());
        this.invoices.saveSubscriptionInvoice(invoice, subscriptionPaymentSuccessTerminatedEvent.getSubscriptionOffer().getSubscriptionOfferId());
        this.users.saveSubscriptionOffer(subscriptionPaymentSuccessTerminatedEvent.getUserId(), subscriptionPaymentSuccessTerminatedEvent.getSubscriptionOffer());
    }
}

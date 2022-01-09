package fr.remy.cc1.application.invoice;

import fr.remy.cc1.application.customer.SubscriptionSuccessTerminatedEvent;
import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.invoice.Invoice;
import fr.remy.cc1.domain.invoice.InvoiceId;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.payment.PaymentState;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.kernel.event.Subscriber;

import java.time.ZonedDateTime;

public class SubscriptionSuccessTerminatedEventInvoiceSubscription implements Subscriber<SubscriptionSuccessTerminatedEvent> {

    private final Invoices invoices;
    private final Users users;

    public SubscriptionSuccessTerminatedEventInvoiceSubscription(Invoices invoices, Users users) {
        this.invoices = invoices;
        this.users = users;
    }

    //TODO voir ce que ça donne si je passe deux subscriptionOffer pour le même user à la suite
    @Override
    public void accept(SubscriptionSuccessTerminatedEvent subscriptionSuccessTerminatedEvent) {
        final InvoiceId invoiceId = this.invoices.nextIdentity();
        Invoice invoice = Invoice.of(invoiceId, subscriptionSuccessTerminatedEvent.getMoney(), subscriptionSuccessTerminatedEvent.getUserId(), PaymentState.VALIDATE, ZonedDateTime.now());
        this.invoices.save(invoice);
        //TODO peut-être mettre un autre event ici
        SubscriptionOffer subscriptionOffer = subscriptionSuccessTerminatedEvent.getSubscriptionOffer().addInvoice(invoice);
        this.users.saveSubscriptionOffer(subscriptionSuccessTerminatedEvent.getUserId(), subscriptionOffer);
    }
}

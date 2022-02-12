package fr.remy.cc1.subscription.application.invoice;

import fr.remy.cc1.subscription.application.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;
import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.invoice.InvoiceId;
import fr.remy.cc1.subscription.domain.invoice.Invoices;
import fr.remy.cc1.subscription.domain.PaymentState;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.kernel.event.Subscriber;

import java.time.ZonedDateTime;

public class SubscriptionPaymentFailedEventInvoiceSubscription implements Subscriber<SubscriptionPaymentFailedEvent> {

    private final Invoices invoices;
    private final Users users;

    public SubscriptionPaymentFailedEventInvoiceSubscription(Invoices invoices, Users users) {
        this.invoices = invoices;
        this.users = users;
    }

    //TODO voir ce que ça donne si je passe deux subscriptionOffer pour le même user à la suite
    @Override
    public void accept(SubscriptionPaymentFailedEvent subscriptionPaymentFailedEvent) {
        final InvoiceId invoiceId = this.invoices.nextIdentity();
        Invoice invoice = Invoice.of(invoiceId, subscriptionPaymentFailedEvent.getMoney(), subscriptionPaymentFailedEvent.getUserId(), PaymentState.REJECTED, ZonedDateTime.now());
        this.invoices.save(invoice);
        SubscriptionOffer subscriptionOffer = subscriptionPaymentFailedEvent.getSubscriptionOffer().addInvoice(invoice);
        this.users.saveSubscriptionOffer(subscriptionPaymentFailedEvent.getUserId(), subscriptionOffer);
    }
}

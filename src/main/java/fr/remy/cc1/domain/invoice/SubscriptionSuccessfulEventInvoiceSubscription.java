package fr.remy.cc1.domain.invoice;

import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.domain.customer.SubscriptionSuccessfulEvent;

public class SubscriptionSuccessfulEventInvoiceSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final Invoices invoices;

    public SubscriptionSuccessfulEventInvoiceSubscription(Invoices invoices) {
        this.invoices = invoices;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        final InvoiceId invoiceId = this.invoices.nextIdentity();
        Invoice invoice = Invoice.of(invoiceId, subscriptionSuccessfulEvent.getSubscriptionOffer().getAmount(), subscriptionSuccessfulEvent.getUser());
        this.invoices.save(invoice);
    }
}

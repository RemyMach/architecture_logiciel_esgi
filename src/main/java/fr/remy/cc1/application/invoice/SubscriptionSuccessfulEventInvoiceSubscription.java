package fr.remy.cc1.application.invoice;

import fr.remy.cc1.domain.invoice.Invoice;
import fr.remy.cc1.domain.invoice.InvoiceId;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.application.customer.SubscriptionSuccessfulEvent;

import java.time.ZonedDateTime;

public class SubscriptionSuccessfulEventInvoiceSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final Invoices invoices;

    public SubscriptionSuccessfulEventInvoiceSubscription(Invoices invoices) {
        this.invoices = invoices;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        final InvoiceId invoiceId = this.invoices.nextIdentity();
        Invoice invoice = Invoice.of(invoiceId, subscriptionSuccessfulEvent.getMoney(), subscriptionSuccessfulEvent.getUserId(), ZonedDateTime.now());
        this.invoices.save(invoice);
    }
}

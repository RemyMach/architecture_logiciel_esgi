package fr.remy.cc1.infrastructure.invoices;

import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.payment.Invoice;
import fr.remy.cc1.domain.payment.InvoiceId;
import fr.remy.cc1.domain.payment.Invoices;
import fr.remy.cc1.infrastructure.SubscriptionSuccessfulEvent;

public class SubscriptionSuccessfulEventInvoiceSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final Invoices invoices;

    public SubscriptionSuccessfulEventInvoiceSubscription(Invoices invoices) {
        this.invoices = invoices;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        System.out.println("on enregistre la facture");
        final InvoiceId invoiceId = this.invoices.nextIdentity();
        Invoice invoice = Invoice.of(invoiceId, subscriptionSuccessfulEvent.getSubscriptionOffer().getAmount(), subscriptionSuccessfulEvent.getUser());
        this.invoices.save(invoice);
    }
}

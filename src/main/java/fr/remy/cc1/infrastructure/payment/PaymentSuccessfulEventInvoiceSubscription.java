package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.payment.Invoice;
import fr.remy.cc1.domain.payment.Invoices;

public class PaymentSuccessfulEventInvoiceSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final Invoices invoices;

    public PaymentSuccessfulEventInvoiceSubscription(Invoices invoices) {
        this.invoices = invoices;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        Invoice invoice = Invoice.of(subscriptionSuccessfulEvent.getSubscriptionOffer().getAmount(), subscriptionSuccessfulEvent.getUser());
        this.invoices.save(invoice);
    }
}

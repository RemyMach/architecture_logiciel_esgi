package fr.remy.cc1.infrastructure.InMemory;

import fr.remy.cc1.subscription.domain.customer.SubscriptionOfferId;
import fr.remy.cc1.subscription.domain.invoice.Invoice;

import java.util.List;
import java.util.Map;

public class SubscriptionInvoiceData {

    private static volatile SubscriptionInvoiceData instance;

    public final Map<SubscriptionOfferId, List<Invoice>> data;

    private SubscriptionInvoiceData(Map<SubscriptionOfferId, List<Invoice>> data) {
        this.data = data;
    }

    public static SubscriptionInvoiceData getInstance() {

        synchronized(SubscriptionInvoiceData.class) {
            if (instance == null) {
                throw new Error("you have to use setUp function to use ContractorsData");
            }
            return instance;
        }
    }

    public static void setup(Map<SubscriptionOfferId, List<Invoice>> data) {
        synchronized(SubscriptionInvoiceData.class) {
            instance = new SubscriptionInvoiceData(data);
        }
    }
}

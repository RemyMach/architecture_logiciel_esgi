package fr.remy.cc1.domain.customer;

import fr.remy.cc1.domain.invoice.Invoice;
import fr.remy.cc1.domain.payment.Money;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionOffer {

    private final Money money;

    private final int discountPercentage;

    private final List<Invoice> invoiceList;

    private SubscriptionOffer(Money money, int discountPercentage, List<Invoice> invoiceList) {
        this.money = money;
        this.discountPercentage = discountPercentage;
        this.invoiceList = invoiceList;
    }

    public static SubscriptionOffer of(Money money, int discountPercentage, List<Invoice> invoiceList) {
       return new SubscriptionOffer(money, discountPercentage, invoiceList);
    }

    public static SubscriptionOffer of(Money money, int discountPercentage) {
        return new SubscriptionOffer(money, discountPercentage, new ArrayList<>());
    }

    public SubscriptionOffer addInvoice(Invoice invoice) {
        List<Invoice> invoiceListCopy = new ArrayList<>(List.copyOf(this.invoiceList));
        invoiceListCopy.add(invoice);
        return new SubscriptionOffer(this.money, this.discountPercentage, invoiceListCopy);
    }

    public Money getMoney() {
        return money;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }
}

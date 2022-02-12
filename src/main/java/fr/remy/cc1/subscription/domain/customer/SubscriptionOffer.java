package fr.remy.cc1.subscription.domain.customer;

import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SubscriptionOffer {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionOffer that = (SubscriptionOffer) o;
        return discountPercentage == that.discountPercentage && Objects.equals(money, that.money) && Objects.equals(invoiceList, that.invoiceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, discountPercentage, invoiceList);
    }
}

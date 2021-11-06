package fr.remy.cc1.infrastructure.creditcards;

import fr.remy.cc1.domain.CreditCard;
import fr.remy.cc1.domain.CreditCards;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.payment.Invoice;
import fr.remy.cc1.domain.payment.Invoices;
import fr.remy.cc1.infrastructure.payment.SubscriptionSuccessfulEvent;

public class SaveCreditCardEventSubscription implements Subscriber<SaveCreditCardEvent> {

    private final CreditCards creditCards;

    public SaveCreditCardEventSubscription(CreditCards creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public void accept(SaveCreditCardEvent saveCreditCardEvent) {
        this.creditCards.save(saveCreditCardEvent.getCreditCard(), saveCreditCardEvent.getUser());
    }
}

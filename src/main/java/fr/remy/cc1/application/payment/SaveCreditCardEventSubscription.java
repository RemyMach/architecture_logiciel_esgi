package fr.remy.cc1.application.payment;

import fr.remy.cc1.domain.payment.creditcard.CreditCardService;
import fr.remy.cc1.kernel.event.Subscriber;


public class SaveCreditCardEventSubscription implements Subscriber<SaveCreditCardEvent> {

    private final CreditCardService creditCardService;

    public SaveCreditCardEventSubscription(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @Override
    public void accept(SaveCreditCardEvent saveCreditCardEvent) {
        this.creditCardService.save(saveCreditCardEvent.getCreditCard(), saveCreditCardEvent.getUser());
    }
}

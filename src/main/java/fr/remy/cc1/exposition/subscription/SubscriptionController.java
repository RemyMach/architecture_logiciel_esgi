package fr.remy.cc1.exposition.subscription;

import fr.remy.cc1.application.CreateSubscriptionOffer;
import fr.remy.cc1.application.CreateSubscriptionOfferCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler;

    @Autowired
    public SubscriptionController(CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler) {
        this.createSubscriptionOfferCommandHandler = createSubscriptionOfferCommandHandler;
    }

    //TODO le if pour le moyen de paiement on peut le mettre ici je pense
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid SubscriptionRequest request) {
        System.out.println(request);
        CreateSubscriptionOffer createSubscriptionOffer = new CreateSubscriptionOffer(
                request.discountPercentage,
                request.amount,
                request.userId,
                request.payment.currency,
                request.payment.name,
                request.payment.saveCreditCard,
                request.payment.creditCardNumber,
                request.payment.creditCardExpiryDate,
                request.payment.creditCardSecurityCode,
                request.payment.creditCardName);
        createSubscriptionOfferCommandHandler.handle(createSubscriptionOffer);
        return ResponseEntity.ok(null);
    }

}

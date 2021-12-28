package fr.remy.cc1.exposition;

import fr.remy.cc1.application.CreateSubscriptionOffer;
import fr.remy.cc1.application.CreateSubscriptionOfferCommandHandler;
import fr.remy.cc1.application.CreateUser;
import fr.remy.cc1.application.CreateUserCommandHandler;
import fr.remy.cc1.domain.user.UserCategoryCreator;
import fr.remy.cc1.kernel.error.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

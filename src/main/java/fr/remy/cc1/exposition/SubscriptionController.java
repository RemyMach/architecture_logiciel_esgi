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
@RequestMapping("/subscription")
public class SubscriptionController {
    private final CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler;

    @Autowired
    public SubscriptionController(CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler) {
        this.createSubscriptionOfferCommandHandler = createSubscriptionOfferCommandHandler;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid SubscriptionRequest request) throws ValidationException {
        CreateSubscriptionOffer createSubscriptionOffer = new CreateSubscriptionOffer(
                request.discountPercentage,
                request.amount,
                request.userId,
                request.payment.currency,
                request.payment.payment,
                request.payment.saveCreditCard,
                request.payment.creditCardNumber,
                request.payment.creditCardExpiryDate,
                request.payment.creditCardSecurityCode,
                request.payment.creditCardName);
        createSubscriptionOfferCommandHandler.handle(createSubscriptionOffer);
        return ResponseEntity.ok(null);
    }

}

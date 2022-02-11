package fr.remy.cc1.subscription.exposition;

import fr.remy.cc1.subscription.application.payment.CreatePayment;
import fr.remy.cc1.subscription.application.payment.CreatePaymentCommandHandler;
import fr.remy.cc1.subscription.application.CreateSubscriptionOffer;
import fr.remy.cc1.subscription.application.CreateSubscriptionOfferCommandHandler;
import fr.remy.cc1.member.domain.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler;
    private final CreatePaymentCommandHandler createPaymentCommandHandler;

    @Autowired
    public SubscriptionController(CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler, CreatePaymentCommandHandler createPaymentCommandHandler) {
        this.createSubscriptionOfferCommandHandler = createSubscriptionOfferCommandHandler;
        this.createPaymentCommandHandler = createPaymentCommandHandler;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid SubscriptionRequest request) throws Exception {
        CreatePayment createPayment = new CreatePayment(
                request.payment.name,
                request.payment.card.creditCardNumber,
                request.payment.card.creditCardExpiryDate,
                request.payment.card.creditCardSecurityCode,
                request.payment.card.creditCardName,
                UserId.of(request.userId)
        );

        createPaymentCommandHandler.handle(createPayment);
        CreateSubscriptionOffer createSubscriptionOffer = new CreateSubscriptionOffer(
                request.discountPercentage,
                request.amount,
                UserId.of(request.userId),
                request.payment.currency,
                request.payment.name
        );
        createSubscriptionOfferCommandHandler.handle(createSubscriptionOffer);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}

package fr.remy.cc1.exposition.subscription;

import fr.remy.cc1.application.CreatePayment;
import fr.remy.cc1.application.CreatePaymentCommandHandler;
import fr.remy.cc1.application.CreateSubscriptionOffer;
import fr.remy.cc1.application.CreateSubscriptionOfferCommandHandler;
import fr.remy.cc1.domain.payment.Payer;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.payment.PaymentDirector;
import fr.remy.cc1.domain.payment.PaymentMethodValidator;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
                request.payment.creditCardNumber,
                request.payment.creditCardExpiryDate,
                request.payment.creditCardSecurityCode,
                request.payment.creditCardName,
                UserId.of(request.userId)
        );
        createPaymentCommandHandler.handle(createPayment);
        System.out.println(createPayment);
        CreateSubscriptionOffer createSubscriptionOffer = new CreateSubscriptionOffer(
                request.discountPercentage,
                request.amount,
                UserId.of(request.userId),
                request.payment.currency,
                request.payment.name
        );
        createSubscriptionOfferCommandHandler.handle(createSubscriptionOffer);
        return ResponseEntity.ok(null);
    }

}

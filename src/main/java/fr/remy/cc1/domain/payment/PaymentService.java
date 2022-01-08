package fr.remy.cc1.domain.payment;

import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.application.customer.SubscriptionSuccessfulEvent;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;

public class PaymentService {

    private final Payment payment;


    public PaymentService(Payment payment) {
        this.payment = payment;
    }

    public void paySubscription(SubscriptionOffer subscriptionOffer, User user) throws PaymentProcessValidationException {

        this.payment.start(subscriptionOffer.getMoney());
        UserCreationEventBus.getInstance().send(
                SubscriptionSuccessfulEvent.of(
                        user.getUserId(),
                        subscriptionOffer,
                        subscriptionOffer.getMoney(),
                        new UserDTO(user.getUserId(),
                                user.getLastname(),
                                user.getLastname(),
                                user.getEmail()
                        )
                )
        );
    }
}

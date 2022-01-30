package fr.remy.cc1.application.payment;

import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.application.customer.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.application.customer.SubscriptionSuccessTerminatedEvent;
import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

public class PaymentService {

    private final Payment payment;
    private final EventBus<Event> eventBus;

    public PaymentService(Payment payment, EventBus<Event> eventBus) {
        this.payment = payment;
        this.eventBus = eventBus;
    }

    public void paySubscription(SubscriptionOffer subscriptionOffer, User user) throws PaymentProcessValidationException {

        try {
            this.payment.start(subscriptionOffer.getMoney());
            this.eventBus.send(
                    SubscriptionSuccessTerminatedEvent.of(
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
        } catch(PaymentProcessValidationException paymentProcessValidationException) {
            this.eventBus.send(
                    SubscriptionPaymentFailedEvent.of(
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
            throw paymentProcessValidationException;
        }
    }
}

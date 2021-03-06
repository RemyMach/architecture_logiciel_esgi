package fr.remy.cc1.subscription.application.payment;

import fr.remy.cc1.kernel.error.PaymentProcessValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.member.application.UserDTO;
import fr.remy.cc1.shared.domain.User;
import fr.remy.cc1.subscription.application.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.subscription.application.SubscriptionPaymentSuccessTerminatedEvent;
import fr.remy.cc1.subscription.domain.Payment;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;

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
                    SubscriptionPaymentSuccessTerminatedEvent.of(
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

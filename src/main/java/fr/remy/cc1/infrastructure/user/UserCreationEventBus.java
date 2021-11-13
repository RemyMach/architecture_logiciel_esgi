package fr.remy.cc1.infrastructure.user;

import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.event.Subscriber;

import java.util.List;
import java.util.Map;


public class UserCreationEventBus<E extends Event> implements EventBus<E>  {

    private Map<Class<E>, List<Subscriber<E>>> subscribers;

    private static UserCreationEventBus userCreationEventBusInstance;

    private UserCreationEventBus() { }

    public static UserCreationEventBus getInstance() {
        if (userCreationEventBusInstance == null) {
            userCreationEventBusInstance = new UserCreationEventBus();
        }
        return userCreationEventBusInstance;
    }

    public void setSubscribers(Map<Class<E>, List<Subscriber<E>>> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public void send(E event) {
        final List<Subscriber<E>> subscribers = this.subscribers.get(event.getClass());
        if (subscribers == null || subscribers.isEmpty()) {
            throw new IllegalStateException("No subscriber for " + event.getClass().getSimpleName());
        }
        subscribers.forEach(subscriber -> subscriber.accept(event));
    }

    @Override
    public void registerSubscriber(Class<E> classE, List<Subscriber<E>> givenSubscribers) {
        subscribers.putIfAbsent(classE, givenSubscribers);
    }
}

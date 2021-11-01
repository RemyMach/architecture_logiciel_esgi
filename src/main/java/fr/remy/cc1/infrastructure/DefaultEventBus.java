package fr.remy.cc1.infrastructure;

import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.event.Subscriber;

import java.util.List;
import java.util.Map;


public class DefaultEventBus<E extends Event> implements EventBus<E>  {

    private Map<Class<E>, List<Subscriber<E>>> subscribers;

    public DefaultEventBus(Map<Class<E>, List<Subscriber<E>>> subscribers) {
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

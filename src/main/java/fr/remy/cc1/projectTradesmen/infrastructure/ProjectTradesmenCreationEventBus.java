package fr.remy.cc1.projectTradesmen.infrastructure;

import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.kernel.event.Subscriber;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectTradesmenCreationEventBus<E extends Event> implements EventBus<E> {
    private final Map<Class<E>, List<Subscriber<E>>> subscribers = new ConcurrentHashMap<>();

    private static final ProjectTradesmenCreationEventBus INSTANCE = new ProjectTradesmenCreationEventBus();

    private ProjectTradesmenCreationEventBus() {
    }

    public static ProjectTradesmenCreationEventBus getInstance() {
        return INSTANCE;
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
    public void registerSubscriber(Class<E> classE, List<Subscriber<E>> subscribers) {
        this.subscribers.putIfAbsent(classE, subscribers);
    }
}

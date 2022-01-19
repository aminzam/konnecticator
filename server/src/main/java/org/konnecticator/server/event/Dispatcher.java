package org.konnecticator.server.event;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Dispatcher {

    private List<NotificationListener> notificationListeners;

    public Dispatcher() {

        notificationListeners = new ArrayList<>();
    }

    public void Subscribe(NotificationListener listener) {

        notificationListeners.add(listener);
    }

    public void Notify(Notification notification) {

        notificationListeners.parallelStream().forEach(p -> p.Handle(notification));
    }
}

package org.konnecticator.server;

import org.konnecticator.server.event.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class WebSocketPublisher {

    @Autowired
    Dispatcher dispatcher;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostConstruct
    public void Listen() {
        dispatcher.Subscribe(notification ->
                simpMessagingTemplate.convertAndSend("/topic/test", notification.getKey()));
    }
}

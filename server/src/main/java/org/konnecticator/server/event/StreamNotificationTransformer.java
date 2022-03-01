package org.konnecticator.server.event;

import org.apache.kafka.streams.kstream.ValueTransformerWithKey;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreamNotificationTransformer implements ValueTransformerWithKey<String, String, String> {

    @Autowired
    Dispatcher dispatcher;

    @Override
    public void init(ProcessorContext context) {
        // Noop
    }

    @Override
    public String transform(String readOnlyKey, String value) {

        var notification = new Notification();
        notification.setKey(readOnlyKey);
        notification.setValue(value);
        dispatcher.Notify(notification);
        return value;
    }

    @Override
    public void close() {
        // Noop
    }
}

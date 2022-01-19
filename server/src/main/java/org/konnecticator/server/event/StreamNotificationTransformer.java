package org.konnecticator.server.event;

import org.apache.kafka.streams.kstream.ValueTransformerWithKey;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class StreamNotificationTransformer implements ValueTransformerWithKey {

    @Autowired
    Dispatcher dispatcher;

    @Override
    public void init(ProcessorContext context) {
        // Noop
    }

    @Override
    public Object transform(Object readOnlyKey, Object value) {

        dispatcher.Notify(new Notification());
        return value;
    }

    @Override
    public void close() {
        // Noop
    }
}

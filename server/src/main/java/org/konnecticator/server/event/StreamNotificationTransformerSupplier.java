package org.konnecticator.server.event;
import org.apache.kafka.streams.kstream.ValueTransformerWithKey;
import org.apache.kafka.streams.kstream.ValueTransformerWithKeySupplier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class StreamNotificationTransformerSupplier implements ValueTransformerWithKeySupplier<String, String, String> {

    private ApplicationContext _context;

    public StreamNotificationTransformerSupplier(ApplicationContext context) {

        _context = context;
    }

    @Override
    public ValueTransformerWithKey<String, String, String> get() {

          return (ValueTransformerWithKey<String, String, String>) _context.getBean(StreamNotificationTransformer.class);
    }

}
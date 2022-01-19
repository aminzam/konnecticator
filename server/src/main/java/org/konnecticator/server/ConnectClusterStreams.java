package org.konnecticator.server;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.konnecticator.server.config.ConfigurationProvider;
import org.konnecticator.server.config.ServerConfiguration;
import org.konnecticator.server.event.Dispatcher;
import org.konnecticator.server.event.StreamNotificationTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectClusterStreams {

    @Autowired
    private ConfigurationProvider configurationProvider;

    @Autowired
    private Dispatcher dispatcher;

    private final Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    @Bean
    public ServerConfiguration getServerConfiguration() {

        return configurationProvider.getServerConfiguration();
    }

    @Bean
    public KTable<Object, Object> buildOffsets(final StreamsBuilder builder, ServerConfiguration serverConfiguration) {

        return createKTable(builder,
                serverConfiguration.getOffsetsTopicName(),
                serverConfiguration.getOffsetsStateStoreName());
    }

    @Bean
    public KTable<Object, Object> buildOConfigs(final StreamsBuilder builder, ServerConfiguration serverConfiguration) {

        return createKTable(builder,
                serverConfiguration.getConfigsTopicName(),
                serverConfiguration.getConfigsStateStoreName());
    }

    @Bean
    public KTable<Object, Object> buildStatus(final StreamsBuilder builder, ServerConfiguration serverConfiguration) {

        return createKTable(builder,
                serverConfiguration.getStatusTopicName(),
                serverConfiguration.getStatusStateStoreName());
    }

    private KTable<Object, Object> createKTable(final StreamsBuilder builder, final String topicName, final String storeName) {

        return builder.table(topicName)
                .transformValues(() -> new StreamNotificationTransformer(),
                        Materialized.as(storeName));
    }
}

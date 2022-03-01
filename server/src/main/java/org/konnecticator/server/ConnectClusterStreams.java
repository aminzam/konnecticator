package org.konnecticator.server;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.konnecticator.server.config.ConfigurationProvider;
import org.konnecticator.server.config.ServerConfiguration;
import org.konnecticator.server.event.StreamNotificationTransformerSupplier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectClusterStreams {

    private ConfigurationProvider configurationProvider;
    private ApplicationContext context;

    public ConnectClusterStreams(ConfigurationProvider configurationProvider, ApplicationContext context) {

        this.configurationProvider = configurationProvider;
        this.context = context;
    }

    @Bean
    public ServerConfiguration getServerConfiguration() {

        return configurationProvider.getServerConfiguration();
    }

    @Bean
    public KTable<String, String> buildOffsets(final StreamsBuilder builder, ServerConfiguration serverConfiguration) {

        return createKTableWithNotifications(builder,
                serverConfiguration.getOffsetsTopicName(),
                serverConfiguration.getOffsetsStateStoreName());
    }

    @Bean
    public KTable<String, String> buildOConfigs(final StreamsBuilder builder, ServerConfiguration serverConfiguration) {

        return createKTableWithNotifications(builder,
                serverConfiguration.getConfigsTopicName(),
                serverConfiguration.getConfigsStateStoreName());
    }

    @Bean
    public KTable<String, String> buildStatus(final StreamsBuilder builder, ServerConfiguration serverConfiguration) {

        return createKTableWithNotifications(builder,
                serverConfiguration.getStatusTopicName(),
                serverConfiguration.getStatusStateStoreName());
    }

    private KTable<String, String> createKTableWithNotifications(final StreamsBuilder builder, final String topicName, final String storeName) {

        StreamNotificationTransformerSupplier supplier = this.context.getBean(StreamNotificationTransformerSupplier.class);
        return builder
            .<String, String>table(topicName)
            .transformValues(supplier, Materialized.as(storeName));
    }
}

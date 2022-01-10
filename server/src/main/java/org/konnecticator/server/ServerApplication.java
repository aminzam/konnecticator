package org.konnecticator.server;

import org.apache.catalina.Server;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.konnecticator.server.config.ConfigurationProvider;
import org.konnecticator.server.config.ServerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class ServerApplication {

	@Autowired
	ConfigurationProvider configurationProvider;

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public ServerConfiguration getServerConfiguration() {

		return configurationProvider.getServerConfiguration();
	}

	@Bean
	KTable<String, String> buildOffsets(final StreamsBuilder builder, ServerConfiguration serverConfiguration) {

		final KTable<String, String> table = builder.table(serverConfiguration.getOffsetsTopicName(),
				Materialized.as(serverConfiguration.getOffsetsStateStoreName()));

		return table;
	}

	@Bean
	KTable<String, String> buildOConfigs(final StreamsBuilder builder, ServerConfiguration serverConfiguration) {

		final KTable<String, String> table = builder.table(serverConfiguration.getConfigsTopicName(),
				Materialized.as(serverConfiguration.getConfigsStateStoreName()));

		return table;
	}

	@Bean
	KTable<String, String> buildStatus(final StreamsBuilder builder, ServerConfiguration serverConfiguration) {

		final KTable<String, String> table = builder.table(serverConfiguration.getStatusTopicName(),
				Materialized.as(serverConfiguration.getStatusStateStoreName()));

		return table;
	}
}

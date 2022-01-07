package org.konnecticator.server;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class ServerApplication {

	@Value("${spring.konnecticator.offsets-store-name}")
	private String offsetsStoreName;

	@Value("${spring.konnecticator.offsets-cluster-topic}")
	private String offsetsClusterTopic;

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	KTable<String, String> countAgg(final StreamsBuilder builder) {

		final KTable<String, String> table = builder.table(offsetsClusterTopic, Materialized.as(offsetsStoreName));

		return table;
	}
}

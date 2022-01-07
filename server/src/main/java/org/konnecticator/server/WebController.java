package org.konnecticator.server;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {

    @Value("${spring.konnecticator.offsets-store-name}")
    private String offsetsStoreName;

    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    @GetMapping("/offsets/")
    public List<KeyValue<String, String>> getOffsetsStateStore() {

        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, String> store = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType(offsetsStoreName, QueryableStoreTypes.keyValueStore())
        );

        List<KeyValue<String, String>> resultList = new ArrayList<>();
        store.all().forEachRemaining(resultList::add);

        return resultList;
    }
}
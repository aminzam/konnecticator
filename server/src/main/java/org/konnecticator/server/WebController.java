package org.konnecticator.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.konnecticator.server.config.ConfigurationProvider;
import org.konnecticator.server.config.ServerConfiguration;
import org.konnecticator.server.connect.rest.RestClient;
import org.konnecticator.server.connect.models.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class WebController {

    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

    @Autowired
    ConfigurationProvider configurationProvider;

    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private ServerConfiguration serverConfiguration;

    @Autowired
    RestClient restClient;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/offsets/")
    public HashMap<String, Status> getOffsetsStateStore() {

        var activeConnectorNames = restClient.getConnectorNames();

        for(int i = 0; i < activeConnectorNames.length; i++)
            logger.info(activeConnectorNames[i]);

        //TODO: move all this shared code of reading and parsing json to separate file
        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, String> store = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType(serverConfiguration.getOffsetsStateStoreName(), QueryableStoreTypes.keyValueStore())
        );

        List<KeyValue<String, String>> resultList = new ArrayList<>();
        store.all().forEachRemaining(resultList::add);

        HashMap<String, Status> statusResultList = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        for (KeyValue<String, String> keyValue : resultList) {
            try {
                Object[] ok = mapper.readValue(keyValue.key, new TypeReference<>() {});
                if (ok.length > 0 && ok[0] instanceof String)
                    if (Arrays.stream(activeConnectorNames).anyMatch(s -> ok[0].equals(s)))
                        statusResultList.put(ok[0].toString(), mapper.readValue(keyValue.value, Status.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return statusResultList;
    }

    @GetMapping("/configs/")
    public List<KeyValue<String, String>> getConfigsStateStore() {

        ServerConfiguration config = configurationProvider.getServerConfiguration();

        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, String> store = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType(config.getConfigsStateStoreName(), QueryableStoreTypes.keyValueStore())
        );

        List<KeyValue<String, String>> resultList = new ArrayList<>();
        store.all().forEachRemaining(resultList::add);

        return resultList;
    }

    @GetMapping("/status/")
    public List<KeyValue<String, String>> getStatusStateStore() {

        ServerConfiguration config = configurationProvider.getServerConfiguration();

        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, String> store = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType(config.getStatusStateStoreName(), QueryableStoreTypes.keyValueStore())
        );

        List<KeyValue<String, String>> resultList = new ArrayList<>();
        store.all().forEachRemaining(resultList::add);

        return resultList;
    }

    @PostMapping("/connector/restart/{name}")
    public boolean restart(@PathVariable String name) {

        //TODO: get proper task id
        restClient.restart(name, "0");

        return true;
    }
}
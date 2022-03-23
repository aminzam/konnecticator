package org.konnecticator.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.konnecticator.server.api.models.ConnectorSummary;
import org.konnecticator.server.api.models.Task;
import org.konnecticator.server.config.ConfigurationProvider;
import org.konnecticator.server.config.ServerConfiguration;
import org.konnecticator.server.connect.rest.RestClient;
import org.konnecticator.server.connect.models.Status;
import org.konnecticator.server.connect.rest.models.ConnectorExpanded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
    @GetMapping("/connectors/")
    public List<ConnectorSummary> getConnectorSummaries() throws JsonProcessingException {

        Map<String, Status> statusStore = getStreamsStatusStateStore();

        Map<String, ConnectorExpanded> connectorExpandedMap = restClient.getConnectorsWithStatusAndInfo();

        Iterator<String> connectorNames = connectorExpandedMap.keySet().iterator();
        List<ConnectorSummary> result = new ArrayList<>();

        while(connectorNames.hasNext()) {

            String connectorName = connectorNames.next();

            // Read values from rest API results to connector
            ConnectorExpanded connectorExpanded = connectorExpandedMap.get(connectorName);
            var summary = new ConnectorSummary();
            summary.setName(connectorName);
            summary.setState(connectorExpanded.getStatus().getConnector().getState());
            summary.setType(connectorExpanded.getStatus().getType());
            summary.setTimestampColumnName(connectorExpanded.getInfo().getConfig().getTimestampColumnName());
            summary.setIncrementingColumnName(connectorExpanded.getInfo().getConfig().getIncrementingColumnName());
            summary.setTasks(connectorExpanded.getStatus().getTasks().stream().map(taskStatus -> {
                var task = new Task();
                task.setId(taskStatus.getId().toString());
                task.setState(taskStatus.getState());
                return task;
            }).collect(Collectors.toList()));

            // Now append offset information from state store
            if(statusStore.containsKey(connectorName)) {
                var status = statusStore.get(connectorName);
                if(status.getIncrementing() != null)
                    summary.setIncrementingOffset(status.getIncrementing().toString());
                if(status.getTimestamp() != null)
                    summary.setTimestampOffset(status.getTimestamp().toString());
            }
            result.add(summary);
        }

        return result;
    }

    private Map<String, Status> getStreamsStatusStateStore() {

        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, String> store = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType(serverConfiguration.getOffsetsStateStoreName(), QueryableStoreTypes.keyValueStore())
        );
        KeyValueIterator<String, String> allStoreElements = store.all();
        Map<String, Status> statusStoreParsed = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        while(allStoreElements.hasNext()) {

            KeyValue<String, String> storeElement = allStoreElements.next();
            try {
                Object[] ok = mapper.readValue(storeElement.key, new TypeReference<>() {});
                if (ok.length > 0 && ok[0] instanceof String) {
                    statusStoreParsed.put(ok[0].toString(), mapper.readValue(storeElement.value, Status.class));
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return statusStoreParsed;
    }

    @PostMapping("/connector/restart/{name}")
    public boolean restart(@PathVariable String name) {

        //TODO: get proper task id
        restClient.restart(name, "0");

        return true;
    }
}
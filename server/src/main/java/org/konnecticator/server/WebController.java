package org.konnecticator.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.kafka.common.metrics.Stat;
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
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class WebController {

    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

    @Autowired
    ConfigurationProvider configurationProvider;

    @Autowired
    RestClient restClient;

    @Autowired
    private StreamBridge streamBridge;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/connectors/")
    public List<ConnectorSummary> getConnectorSummaries() {

        Map<String, Status> statusStore = getStreamsStatusStateStore();
        Map<String, ConnectorExpanded> connectorExpandedMap;
        List<ConnectorSummary> result = new ArrayList<>();

        try {
            connectorExpandedMap = restClient.getConnectorsWithStatusAndInfo();
        } catch (Exception exception) {
            logger.error("Error while contacting cluster API: {}", exception.getMessage());
            logger.debug(exception.toString());
            return result;
        }

        Iterator<String> connectorNames = connectorExpandedMap.keySet().iterator();

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

        KeyValueIterator<String, String> allStoreElements;
        try {
            ServerConfiguration serverConfiguration = configurationProvider.getServerConfiguration();
            KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
            ReadOnlyKeyValueStore<String, String> store = kafkaStreams.store(
                    StoreQueryParameters.fromNameAndType(serverConfiguration.getOffsetsStateStoreName(), QueryableStoreTypes.keyValueStore())
            );
            allStoreElements = store.all();
        } catch(Exception ex) {

            logger.error("Cannot get state store: {}", ex.getMessage());
            logger.debug(ex.toString());
            return new HashMap<>();
        }
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
                logger.error("Error while parsing offset data: {}", e.getMessage());
                logger.debug(e.toString());
            }
        }
        return statusStoreParsed;
    }

    @PostMapping("/connectors/{connectorName}/tasks/{taskId}/restart")
    public boolean restart(@PathVariable String connectorName, @PathVariable String taskId) {

        try {

            restClient.restart(connectorName, taskId);
            return true;

        } catch(Exception exception) {

            logger.error("Exception sending restart request to Connect cluster: ", exception);
            return false;
        }
    }

    @PostMapping("/connectors/{connectorName}/offset/reset")
    public boolean resetOffset(@PathVariable String connectorName, Status newOffset) {

        ServerConfiguration serverConfiguration = configurationProvider.getServerConfiguration();
        String offsetTopicName = serverConfiguration.getOffsetsTopicName();

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();

        try {

            String[] key = new String[] {connectorName, "{\"query\":\"query\"}"};
            String valueJson = writer.writeValueAsString(newOffset);
            String keyJson = writer.writeValueAsString(key);

            Message<String> message = MessageBuilder
                    .withPayload(valueJson)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, keyJson.getBytes())
                    .build();
            streamBridge.send(offsetTopicName, message);

        } catch (JsonProcessingException e) {
            logger.error("Error while restarting connector: {}", e.getMessage());
            logger.debug(e.toString());
        }

        //restClient.restart(name, "0");

        return true;
    }
}
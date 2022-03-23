package org.konnecticator.server.connect.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.konnecticator.server.config.ConfigurationProvider;
import org.konnecticator.server.config.ServerConfiguration;
import org.konnecticator.server.connect.rest.models.ConnectorExpanded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class RestClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private ConfigurationProvider configurationProvider;

    public void restart(String workerName, String taskId) {

        ServerConfiguration config = configurationProvider.getServerConfiguration();

        RestTemplate template = restTemplateBuilder.build();

        HttpEntity<String> entity = GetBaseEntity();

        template.postForLocation(config.getClusterRestEndPoint() + "/connectors/{workerName}/tasks/{taskId}/restart", entity, workerName, taskId);
    }

    public String[] getConnectorNames() {

        ServerConfiguration config = configurationProvider.getServerConfiguration();

        RestTemplate template = restTemplateBuilder.build();

        ResponseEntity<String[]> responseEntity = template.getForEntity(config.getClusterRestEndPoint() + "/connectors", String[].class);

        return responseEntity.getBody();
    }

    public Map<String, ConnectorExpanded> getConnectorsWithStatusAndInfo() throws JsonProcessingException {

        var mapper = new ObjectMapper();

        ServerConfiguration config = configurationProvider.getServerConfiguration();

        RestTemplate template = restTemplateBuilder.build();

        String responseString = template.getForObject(config.getClusterRestEndPoint() + "/connectors?expand=status&expand=info", String.class);

        JsonNode node = mapper.readTree(responseString);

        var result = new HashMap<String, ConnectorExpanded>();

        System.out.println("responseString: " + responseString);

        Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
        while(iterator.hasNext()) {
            Map.Entry<String, JsonNode> field = iterator.next();
            String nodeString = field.getValue().toString();

            System.out.println("nodeString: " + nodeString);

            result.put(field.getKey(), mapper.readValue(nodeString, ConnectorExpanded.class));
        }

        return result;
    }

    private <T> HttpEntity<T> GetBaseEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<>(null, headers);
        return entity;
    }
}

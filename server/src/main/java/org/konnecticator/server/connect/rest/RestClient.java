package org.konnecticator.server.connect.rest;

import org.konnecticator.server.config.ConfigurationProvider;
import org.konnecticator.server.config.ServerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

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

        HttpEntity<String> entity = GetBaseEntity();

        ResponseEntity<String[]> responseEntity = template.getForEntity(config.getClusterRestEndPoint() + "/connectors", String[].class);

        return responseEntity.getBody();
    }

    private <T> HttpEntity<T> GetBaseEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<>(null, headers);
        return entity;
    }
}

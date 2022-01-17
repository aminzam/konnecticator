package org.konnecticator.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationProvider {

    @Value("${spring.konnecticator.offsets-store-name}")
    private String offsetsStoreName;

    @Value("${spring.konnecticator.offsets-cluster-topic}")
    private String offsetsClusterTopic;

    @Value("${spring.konnecticator.configs-store-name}")
    private String configsStoreName;

    @Value("${spring.konnecticator.configs-cluster-topic}")
    private String configsClusterTopic;

    @Value("${spring.konnecticator.status-store-name}")
    private String statusStoreName;

    @Value("${spring.konnecticator.status-cluster-topic}")
    private String statusClusterTopic;

    @Value("${spring.konnecticator.connect-cluster-rest-endpoint}")
    private String clusterRestEndPoint;

    public ServerConfiguration getServerConfiguration() {

        return new ServerConfiguration(
                offsetsStoreName,
                offsetsClusterTopic,
                configsStoreName,
                configsClusterTopic,
                statusStoreName,
                statusClusterTopic,
                clusterRestEndPoint);
    }
}

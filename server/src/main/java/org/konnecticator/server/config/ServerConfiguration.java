package org.konnecticator.server.config;

public class ServerConfiguration {

    private String offsetsStateStoreName;
    private String offsetsTopicName;
    private String configsStateStoreName;
    private String configsTopicName;
    private String statusStateStoreName;
    private String statusTopicName;

    public ServerConfiguration(String offsetsStateStoreName,
                               String offsetsTopicName,
                               String configsStateStoreName,
                               String configsTopicName,
                               String statusStateStoreName,
                               String statusTopicName) {

        this.offsetsStateStoreName = offsetsStateStoreName;
        this.offsetsTopicName = offsetsTopicName;
        this.configsStateStoreName = configsStateStoreName;
        this.configsTopicName = configsTopicName;
        this.statusStateStoreName = statusStateStoreName;
        this.statusTopicName = statusTopicName;
    }

    public String getOffsetsStateStoreName() {

        return offsetsStateStoreName;
    }

    public String getOffsetsTopicName() {

        return offsetsTopicName;
    }

    public String getConfigsStateStoreName() {

        return configsStateStoreName;
    }

    public String getConfigsTopicName() {

        return configsTopicName;
    }

    public String getStatusStateStoreName() {
        return statusStateStoreName;
    }

    public String getStatusTopicName() {
        return statusTopicName;
    }
}

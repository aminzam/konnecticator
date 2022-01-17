package org.konnecticator.server.config;

public class ServerConfiguration {

    private String offsetsStateStoreName;
    private String offsetsTopicName;
    private String configsStateStoreName;
    private String configsTopicName;
    private String statusStateStoreName;
    private String statusTopicName;
    private String clusterRestEndPoint;

    public ServerConfiguration(String offsetsStateStoreName,
                               String offsetsTopicName,
                               String configsStateStoreName,
                               String configsTopicName,
                               String statusStateStoreName,
                               String statusTopicName, String clusterRestEndPoint) {

        this.offsetsStateStoreName = offsetsStateStoreName;
        this.offsetsTopicName = offsetsTopicName;
        this.configsStateStoreName = configsStateStoreName;
        this.configsTopicName = configsTopicName;
        this.statusStateStoreName = statusStateStoreName;
        this.statusTopicName = statusTopicName;
        this.clusterRestEndPoint = clusterRestEndPoint;
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

    public String getClusterRestEndPoint() {

        return clusterRestEndPoint;
    }
}

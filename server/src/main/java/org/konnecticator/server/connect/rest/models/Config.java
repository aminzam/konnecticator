
package org.konnecticator.server.connect.rest.models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "connector.class",
    "timestamp.column.name",
    "incrementing.column.name",
    "topic.creation.default.partitions",
    "connection.password",
    "basic.auth.credentials.source",
    "transforms",
    "config.providers",
    "value.converter.basic.auth.credentials.source",
    "mode",
    "schema.registry.url",
    "topic.prefix",
    "poll.interval.ms",
    "topic.creation.default.replication.factor",
    "config.providers.file.class",
    "value.converter",
    "key.converter",
    "transforms.ValueToKey.type",
    "transforms.ExtractField.field",
    "query",
    "transforms.ValueToKey.fields",
    "value.converter.schema.registry.url",
    "basic.auth.user.info",
    "value.converter.basic.auth.user.info",
    "connection.user",
    "name",
    "numeric.mapping",
    "connection.url",
    "transforms.ExtractField.type"
})
@Generated("jsonschema2pojo")
public class Config {

    @JsonProperty("connector.class")
    private String connectorClass;
    @JsonProperty("timestamp.column.name")
    private String timestampColumnName;
    @JsonProperty("incrementing.column.name")
    private String incrementingColumnName;
    @JsonProperty("topic.creation.default.partitions")
    private String topicCreationDefaultPartitions;
    @JsonProperty("connection.password")
    private String connectionPassword;
    @JsonProperty("basic.auth.credentials.source")
    private String basicAuthCredentialsSource;
    @JsonProperty("transforms")
    private String transforms;
    @JsonProperty("config.providers")
    private String configProviders;
    @JsonProperty("value.converter.basic.auth.credentials.source")
    private String valueConverterBasicAuthCredentialsSource;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("schema.registry.url")
    private String schemaRegistryUrl;
    @JsonProperty("topic.prefix")
    private String topicPrefix;
    @JsonProperty("poll.interval.ms")
    private String pollIntervalMs;
    @JsonProperty("topic.creation.default.replication.factor")
    private String topicCreationDefaultReplicationFactor;
    @JsonProperty("config.providers.file.class")
    private String configProvidersFileClass;
    @JsonProperty("value.converter")
    private String valueConverter;
    @JsonProperty("key.converter")
    private String keyConverter;
    @JsonProperty("transforms.ValueToKey.type")
    private String transformsValueToKeyType;
    @JsonProperty("transforms.ExtractField.field")
    private String transformsExtractFieldField;
    @JsonProperty("query")
    private String query;
    @JsonProperty("transforms.ValueToKey.fields")
    private String transformsValueToKeyFields;
    @JsonProperty("value.converter.schema.registry.url")
    private String valueConverterSchemaRegistryUrl;
    @JsonProperty("basic.auth.user.info")
    private String basicAuthUserInfo;
    @JsonProperty("value.converter.basic.auth.user.info")
    private String valueConverterBasicAuthUserInfo;
    @JsonProperty("connection.user")
    private String connectionUser;
    @JsonProperty("name")
    private String name;
    @JsonProperty("numeric.mapping")
    private String numericMapping;
    @JsonProperty("connection.url")
    private String connectionUrl;
    @JsonProperty("transforms.ExtractField.type")
    private String transformsExtractFieldType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("connector.class")
    public String getConnectorClass() {
        return connectorClass;
    }

    @JsonProperty("connector.class")
    public void setConnectorClass(String connectorClass) {
        this.connectorClass = connectorClass;
    }

    @JsonProperty("timestamp.column.name")
    public String getTimestampColumnName() {
        return timestampColumnName;
    }

    @JsonProperty("timestamp.column.name")
    public void setTimestampColumnName(String timestampColumnName) {
        this.timestampColumnName = timestampColumnName;
    }

    @JsonProperty("incrementing.column.name")
    public String getIncrementingColumnName() {
        return incrementingColumnName;
    }

    @JsonProperty("incrementing.column.name")
    public void setIncrementingColumnName(String incrementingColumnName) {
        this.incrementingColumnName = incrementingColumnName;
    }

    @JsonProperty("topic.creation.default.partitions")
    public String getTopicCreationDefaultPartitions() {
        return topicCreationDefaultPartitions;
    }

    @JsonProperty("topic.creation.default.partitions")
    public void setTopicCreationDefaultPartitions(String topicCreationDefaultPartitions) {
        this.topicCreationDefaultPartitions = topicCreationDefaultPartitions;
    }

    @JsonProperty("connection.password")
    public String getConnectionPassword() {
        return connectionPassword;
    }

    @JsonProperty("connection.password")
    public void setConnectionPassword(String connectionPassword) {
        this.connectionPassword = connectionPassword;
    }

    @JsonProperty("basic.auth.credentials.source")
    public String getBasicAuthCredentialsSource() {
        return basicAuthCredentialsSource;
    }

    @JsonProperty("basic.auth.credentials.source")
    public void setBasicAuthCredentialsSource(String basicAuthCredentialsSource) {
        this.basicAuthCredentialsSource = basicAuthCredentialsSource;
    }

    @JsonProperty("transforms")
    public String getTransforms() {
        return transforms;
    }

    @JsonProperty("transforms")
    public void setTransforms(String transforms) {
        this.transforms = transforms;
    }

    @JsonProperty("config.providers")
    public String getConfigProviders() {
        return configProviders;
    }

    @JsonProperty("config.providers")
    public void setConfigProviders(String configProviders) {
        this.configProviders = configProviders;
    }

    @JsonProperty("value.converter.basic.auth.credentials.source")
    public String getValueConverterBasicAuthCredentialsSource() {
        return valueConverterBasicAuthCredentialsSource;
    }

    @JsonProperty("value.converter.basic.auth.credentials.source")
    public void setValueConverterBasicAuthCredentialsSource(String valueConverterBasicAuthCredentialsSource) {
        this.valueConverterBasicAuthCredentialsSource = valueConverterBasicAuthCredentialsSource;
    }

    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    @JsonProperty("mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("schema.registry.url")
    public String getSchemaRegistryUrl() {
        return schemaRegistryUrl;
    }

    @JsonProperty("schema.registry.url")
    public void setSchemaRegistryUrl(String schemaRegistryUrl) {
        this.schemaRegistryUrl = schemaRegistryUrl;
    }

    @JsonProperty("topic.prefix")
    public String getTopicPrefix() {
        return topicPrefix;
    }

    @JsonProperty("topic.prefix")
    public void setTopicPrefix(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }

    @JsonProperty("poll.interval.ms")
    public String getPollIntervalMs() {
        return pollIntervalMs;
    }

    @JsonProperty("poll.interval.ms")
    public void setPollIntervalMs(String pollIntervalMs) {
        this.pollIntervalMs = pollIntervalMs;
    }

    @JsonProperty("topic.creation.default.replication.factor")
    public String getTopicCreationDefaultReplicationFactor() {
        return topicCreationDefaultReplicationFactor;
    }

    @JsonProperty("topic.creation.default.replication.factor")
    public void setTopicCreationDefaultReplicationFactor(String topicCreationDefaultReplicationFactor) {
        this.topicCreationDefaultReplicationFactor = topicCreationDefaultReplicationFactor;
    }

    @JsonProperty("config.providers.file.class")
    public String getConfigProvidersFileClass() {
        return configProvidersFileClass;
    }

    @JsonProperty("config.providers.file.class")
    public void setConfigProvidersFileClass(String configProvidersFileClass) {
        this.configProvidersFileClass = configProvidersFileClass;
    }

    @JsonProperty("value.converter")
    public String getValueConverter() {
        return valueConverter;
    }

    @JsonProperty("value.converter")
    public void setValueConverter(String valueConverter) {
        this.valueConverter = valueConverter;
    }

    @JsonProperty("key.converter")
    public String getKeyConverter() {
        return keyConverter;
    }

    @JsonProperty("key.converter")
    public void setKeyConverter(String keyConverter) {
        this.keyConverter = keyConverter;
    }

    @JsonProperty("transforms.ValueToKey.type")
    public String getTransformsValueToKeyType() {
        return transformsValueToKeyType;
    }

    @JsonProperty("transforms.ValueToKey.type")
    public void setTransformsValueToKeyType(String transformsValueToKeyType) {
        this.transformsValueToKeyType = transformsValueToKeyType;
    }

    @JsonProperty("transforms.ExtractField.field")
    public String getTransformsExtractFieldField() {
        return transformsExtractFieldField;
    }

    @JsonProperty("transforms.ExtractField.field")
    public void setTransformsExtractFieldField(String transformsExtractFieldField) {
        this.transformsExtractFieldField = transformsExtractFieldField;
    }

    @JsonProperty("query")
    public String getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(String query) {
        this.query = query;
    }

    @JsonProperty("transforms.ValueToKey.fields")
    public String getTransformsValueToKeyFields() {
        return transformsValueToKeyFields;
    }

    @JsonProperty("transforms.ValueToKey.fields")
    public void setTransformsValueToKeyFields(String transformsValueToKeyFields) {
        this.transformsValueToKeyFields = transformsValueToKeyFields;
    }

    @JsonProperty("value.converter.schema.registry.url")
    public String getValueConverterSchemaRegistryUrl() {
        return valueConverterSchemaRegistryUrl;
    }

    @JsonProperty("value.converter.schema.registry.url")
    public void setValueConverterSchemaRegistryUrl(String valueConverterSchemaRegistryUrl) {
        this.valueConverterSchemaRegistryUrl = valueConverterSchemaRegistryUrl;
    }

    @JsonProperty("basic.auth.user.info")
    public String getBasicAuthUserInfo() {
        return basicAuthUserInfo;
    }

    @JsonProperty("basic.auth.user.info")
    public void setBasicAuthUserInfo(String basicAuthUserInfo) {
        this.basicAuthUserInfo = basicAuthUserInfo;
    }

    @JsonProperty("value.converter.basic.auth.user.info")
    public String getValueConverterBasicAuthUserInfo() {
        return valueConverterBasicAuthUserInfo;
    }

    @JsonProperty("value.converter.basic.auth.user.info")
    public void setValueConverterBasicAuthUserInfo(String valueConverterBasicAuthUserInfo) {
        this.valueConverterBasicAuthUserInfo = valueConverterBasicAuthUserInfo;
    }

    @JsonProperty("connection.user")
    public String getConnectionUser() {
        return connectionUser;
    }

    @JsonProperty("connection.user")
    public void setConnectionUser(String connectionUser) {
        this.connectionUser = connectionUser;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("numeric.mapping")
    public String getNumericMapping() {
        return numericMapping;
    }

    @JsonProperty("numeric.mapping")
    public void setNumericMapping(String numericMapping) {
        this.numericMapping = numericMapping;
    }

    @JsonProperty("connection.url")
    public String getConnectionUrl() {
        return connectionUrl;
    }

    @JsonProperty("connection.url")
    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    @JsonProperty("transforms.ExtractField.type")
    public String getTransformsExtractFieldType() {
        return transformsExtractFieldType;
    }

    @JsonProperty("transforms.ExtractField.type")
    public void setTransformsExtractFieldType(String transformsExtractFieldType) {
        this.transformsExtractFieldType = transformsExtractFieldType;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

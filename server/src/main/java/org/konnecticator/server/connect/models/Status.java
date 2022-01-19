package org.konnecticator.server.connect.models;

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
        "timestamp_nanos",
        "incrementing",
        "timestamp"
})
@Generated("jsonschema2pojo")
public class Status {

    @JsonProperty("timestamp_nanos")
    private Integer timestampNanos;
    @JsonProperty("incrementing")
    private Integer incrementing;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("timestamp_nanos")
    public Integer getTimestampNanos() {
        return timestampNanos;
    }

    @JsonProperty("timestamp_nanos")
    public void setTimestampNanos(Integer timestampNanos) {
        this.timestampNanos = timestampNanos;
    }

    @JsonProperty("incrementing")
    public Integer getIncrementing() {
        return incrementing;
    }

    @JsonProperty("incrementing")
    public void setIncrementing(Integer incrementing) {
        this.incrementing = incrementing;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
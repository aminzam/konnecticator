
package org.konnecticator.server.api.models;

import java.util.HashMap;
import java.util.List;
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
    "name",
    "state",
    "tasks",
    "type",
    "timestamp_column_name",
    "incrementing_column_name",
    "timestamp_offset",
    "incrementing_offset"
})
@Generated("jsonschema2pojo")
public class ConnectorSummary {

    @JsonProperty("name")
    private String name;
    @JsonProperty("state")
    private String state;
    @JsonProperty("tasks")
    private List<Task> tasks = null;
    @JsonProperty("type")
    private String type;
    @JsonProperty("timestamp_column_name")
    private String timestampColumnName;
    @JsonProperty("incrementing_column_name")
    private String incrementingColumnName;
    @JsonProperty("timestamp_offset")
    private String timestampOffset;
    @JsonProperty("incrementing_offset")
    private String incrementingOffset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("tasks")
    public List<Task> getTasks() {
        return tasks;
    }

    @JsonProperty("tasks")
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("timestamp_column_name")
    public String getTimestampColumnName() {
        return timestampColumnName;
    }

    @JsonProperty("timestamp_column_name")
    public void setTimestampColumnName(String timestampColumnName) {
        this.timestampColumnName = timestampColumnName;
    }

    @JsonProperty("incrementing_column_name")
    public String getIncrementingColumnName() {
        return incrementingColumnName;
    }

    @JsonProperty("incrementing_column_name")
    public void setIncrementingColumnName(String incrementingColumnName) {
        this.incrementingColumnName = incrementingColumnName;
    }

    @JsonProperty("timestamp_offset")
    public String getTimestampOffset() {
        return timestampOffset;
    }

    @JsonProperty("timestamp_offset")
    public void setTimestampOffset(String timestampOffset) {
        this.timestampOffset = timestampOffset;
    }

    @JsonProperty("incrementing_offset")
    public String getIncrementingOffset() {
        return incrementingOffset;
    }

    @JsonProperty("incrementing_offset")
    public void setIncrementingOffset(String incrementingOffset) {
        this.incrementingOffset = incrementingOffset;
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

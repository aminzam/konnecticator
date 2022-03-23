
package org.konnecticator.server.connect.rest.models;

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
    "connector",
    "tasks",
    "type"
})
@Generated("jsonschema2pojo")
public class Status {

    @JsonProperty("name")
    private String name;
    @JsonProperty("connector")
    private State connector;
    @JsonProperty("tasks")
    private List<TaskStatus> tasks = null;
    @JsonProperty("type")
    private String type;
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

    @JsonProperty("connector")
    public State getConnector() {
        return connector;
    }

    @JsonProperty("connector")
    public void setConnector(State connector) {
        this.connector = connector;
    }

    @JsonProperty("tasks")
    public List<TaskStatus> getTasks() {
        return tasks;
    }

    @JsonProperty("tasks")
    public void setTasks(List<TaskStatus> tasks) {
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

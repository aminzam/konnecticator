
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
    "connector",
    "task"
})
@Generated("jsonschema2pojo")
public class TaskInfo {

    @JsonProperty("connector")
    private String connector;
    @JsonProperty("task")
    private Integer task;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("connector")
    public String getConnector() {
        return connector;
    }

    @JsonProperty("connector")
    public void setConnector(String connector) {
        this.connector = connector;
    }

    @JsonProperty("task")
    public Integer getTask() {
        return task;
    }

    @JsonProperty("task")
    public void setTask(Integer task) {
        this.task = task;
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

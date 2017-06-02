package com.tim.doordashlite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Home on 5/31/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Business {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;

}

package com.tim.doordashlite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Home on 5/31/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    @JsonProperty("city")
    public String city;
    @JsonProperty("state")
    public String state;
    @JsonProperty("street")
    public String street;
    @JsonProperty("lat")
    public Double lat;
    @JsonProperty("lng")
    public Double lng;
    @JsonProperty("printable_address")
    public String printableAddress;

}

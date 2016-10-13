package de.goeuro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class City {

    @JsonProperty("_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("geo_position")
    private GeoData geoData;

    @JsonProperty("type")
    private String type;
}

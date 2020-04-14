package com.fleboulch.densitypopulation.geography.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaResponse {

    @JsonProperty("min_lat")
    private double minLat;

    @JsonProperty("max_lat")
    private double maxLat;

    @JsonProperty("min_lon")
    private double minLon;

    @JsonProperty("max_lon")
    private double maxLon;
}

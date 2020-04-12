package com.fleboulch.densitypopulation.geography.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LargeAreaInput {

    @JsonProperty("min_lat")
    private double minLat;

    @JsonProperty("min_lon")
    private double minLon;

}

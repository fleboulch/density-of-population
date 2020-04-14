package com.fleboulch.densitypopulation.geography.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CountPoiResponse {

    @JsonProperty("value")
    private int numberOfPoi;
}

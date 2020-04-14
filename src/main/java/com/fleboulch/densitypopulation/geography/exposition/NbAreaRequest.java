package com.fleboulch.densitypopulation.geography.exposition;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NbAreaRequest {

    @JsonProperty("n")
    private int value;

}

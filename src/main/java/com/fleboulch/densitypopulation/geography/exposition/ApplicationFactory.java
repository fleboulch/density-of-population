package com.fleboulch.densitypopulation.geography.exposition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;

public class ApplicationFactory {

    private ApplicationFactory() {
    }

    public static LargeArea toDomain(String input) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LargeAreaInput area = objectMapper.readValue(input, LargeAreaInput.class);
            return LargeArea.of(Coordinates.of(area.getMinLon(), area.getMinLat()));

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Converting input to JSON failed", e);
        }
    }
}

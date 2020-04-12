package com.fleboulch.densitypopulation.geography.exposition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleboulch.densitypopulation.geography.application.GeographyAlgo;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;

public class CommandLineRunner {

    private final GeographyAlgo geographyAlgo;
    private ObjectMapper objectMapper;

    public CommandLineRunner(GeographyAlgo geographyAlgo) {
        this.geographyAlgo = geographyAlgo;
        objectMapper = new ObjectMapper();
    }

    public String computeNbPoi(String inputArea) {
        LargeArea largeArea = ApplicationFactory.toDomain(inputArea);
        int countPois = geographyAlgo.fetchPoiInside(largeArea).size();
        return toResponse(countPois);
    }

    private String toResponse(int countPois) {
        CountPoiResponse response = ApplicationFactory.toResponse(countPois);
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Writing JSON failed");
        }
    }
}

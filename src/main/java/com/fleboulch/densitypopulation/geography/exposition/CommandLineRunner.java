package com.fleboulch.densitypopulation.geography.exposition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleboulch.densitypopulation.geography.application.GeographyAlgo;
import com.fleboulch.densitypopulation.geography.domain.Area;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;

import java.util.Set;

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

    public String fetchDensestArea(String nbAreaRequest) {
        int nbArea = ApplicationFactory.toNbAreaDomain(nbAreaRequest);

        Set<Area> densestAreas = geographyAlgo.fetchDensestArea(nbArea);
        return toResponse(densestAreas);
    }

    private String toResponse(int countPois) {
        CountPoiResponse response = ApplicationFactory.toResponse(countPois);
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Writing JSON failed");
        }
    }

    private String toResponse(Set<Area> densestAreas) {
        Set<AreaResponse> response = ApplicationFactory.toResponses(densestAreas);
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Writing JSON failed");
        }
    }
}

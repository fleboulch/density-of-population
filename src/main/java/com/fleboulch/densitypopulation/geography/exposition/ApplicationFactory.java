package com.fleboulch.densitypopulation.geography.exposition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleboulch.densitypopulation.geography.domain.Area;
import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ApplicationFactory {

    private ApplicationFactory() {
    }

    public static LargeArea toDomain(String input) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LargeAreaRequest area = objectMapper.readValue(input, LargeAreaRequest.class);
            return LargeArea.of(Coordinates.of(area.getMinLon(), area.getMinLat()));

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Converting input to JSON failed", e);
        }
    }

    public static int toNbAreaDomain(String input) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NbAreaRequest request = objectMapper.readValue(input, NbAreaRequest.class);
            return request.getValue();

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Converting input to JSON failed", e);
        }
    }

    public static CountPoiResponse toResponse(int nbPois) {
        return new CountPoiResponse(nbPois);
    }

    public static Set<AreaResponse> toResponses(Set<Area> densestAreas) {
        return densestAreas.stream()
                .map(ApplicationFactory::toResponse)
                .collect(toSet());
    }

    private static AreaResponse toResponse(Area densestArea) {
        return new AreaResponse(
                densestArea.getInclusiveMinCoordinates().getLatitude().getValue(),
                densestArea.getInclusiveMaxCoordinates().getLatitude().getValue(),
                densestArea.getInclusiveMinCoordinates().getLongitude().getValue(),
                densestArea.getInclusiveMaxCoordinates().getLongitude().getValue()
        );
    }
}

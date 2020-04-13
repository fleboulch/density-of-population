package com.fleboulch.densitypopulation.geography.infra;

import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.Poi;

public class InfraFactory {

    private static final String SPLIT_CHARACTER = "\t";

    private InfraFactory() {
    }

    public static Poi toDomain(String row) {
        String[] splittedRow = row.split(SPLIT_CHARACTER);
        String id = splittedRow[0].trim();
        double latitude = Double.parseDouble(splittedRow[1].trim());
        double longitude = Double.parseDouble(splittedRow[2].trim());

        return new Poi(id, Coordinates.of(longitude, latitude));
    }
}

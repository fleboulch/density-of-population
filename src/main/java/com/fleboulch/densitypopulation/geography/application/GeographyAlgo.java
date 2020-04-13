package com.fleboulch.densitypopulation.geography.application;

import com.fleboulch.densitypopulation.geography.domain.LargeArea;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.geography.infra.PoiFileFinder;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class GeographyAlgo {

    private final PoiFileFinder poiFileFinder;

    public GeographyAlgo(PoiFileFinder poiFileFinder) {
        this.poiFileFinder = poiFileFinder;
    }

    public Set<Poi> fetchPoiInside(LargeArea largeArea) {
        Set<Poi> inputPois = poiFileFinder.find();

        return inputPois.stream()
                .filter(largeArea::contains)
                .collect(toSet());
    }
}

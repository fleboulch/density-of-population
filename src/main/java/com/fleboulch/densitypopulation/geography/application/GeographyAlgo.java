package com.fleboulch.densitypopulation.geography.application;

import com.fleboulch.densitypopulation.geography.domain.LargeArea;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.geography.infra.PoiFileFinder;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GeographyAlgo {

    private final PoiFileFinder poiFileFinder;

    public GeographyAlgo(PoiFileFinder poiFileFinder) {
        this.poiFileFinder = poiFileFinder;
    }

    public List<Poi> fetchPoiInside(LargeArea largeArea) {
        List<Poi> inputPois = poiFileFinder.find();

        return inputPois.stream()
                .filter(largeArea::contains)
                .collect(toList());
    }
}

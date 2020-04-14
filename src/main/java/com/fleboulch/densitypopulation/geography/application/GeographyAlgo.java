package com.fleboulch.densitypopulation.geography.application;

import com.fleboulch.densitypopulation.geography.domain.Area;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.geography.infra.PoiFileFinder;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public Set<Area> fetchDensestArea(int nbArea) {
        Set<Poi> pois = poiFileFinder.find();

        Map<Area, Long> map = computePoisIntoMap(pois);

        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(nbArea)
                .map(Map.Entry::getKey)
                .collect(toSet());
    }

    private Map<Area, Long> computePoisIntoMap(Set<Poi> pois) {

        return pois.stream()
                .flatMap(Poi::findNearestAreas)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    }

}

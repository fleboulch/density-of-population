package com.fleboulch.densitypopulation.geography.application;

import com.fleboulch.densitypopulation.geography.domain.Area;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.geography.infra.PoiFileFinder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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

        Map<Area, Integer> map = computePoisIntoMap(pois);

        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(nbArea)
                .map(Map.Entry::getKey)
                .collect(toSet());
    }

    private Map<Area, Integer> computePoisIntoMap(Set<Poi> pois) {
        return pois.stream()
                .map(this::computePoiIntoEntry)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Map.Entry<Area, Integer> computePoiIntoEntry(Poi poi) {
        Set<Area> areas = poi.findNearestAreas();
        return null;
    }
}

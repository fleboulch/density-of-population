package com.fleboulch.densitypopulation.geography.application;

import com.fleboulch.densitypopulation.geography.domain.Area;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.geography.infra.PoiFileFinder;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
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

    public Set<Area> fetchDensestArea(int nbArea) {
        Set<Poi> pois = poiFileFinder.find();

        Map<Area, Integer> map = computePoisIntoMap(pois);

        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(nbArea)
                .map(Map.Entry::getKey)
                .collect(toSet());
    }

    private Map<Area, Integer> computePoisIntoMap(Set<Poi> pois) {
        Map<Area, Integer> map = new HashMap<>();
        for (Poi poi : pois) {
            Set<Area> areas = poi.findNearestAreas();
            for (Area area : areas) {
                int count = map.getOrDefault(area, 0);
                map.put(area, count + 1);
            }
        }
        return map;
    }

}

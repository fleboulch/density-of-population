package com.fleboulch.densitypopulation.geography.infra;

import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.Poi;

import java.util.List;

public class PoiFileFinder {

    public List<Poi> find() {
        return List.of(new Poi("id1", Coordinates.of(0, 0)));
    }
}

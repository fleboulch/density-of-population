package com.fleboulch.densitypopulation.geography.domain;

public class LargeArea extends Area {

    private LargeArea(Coordinates minCoordinates, Coordinates maxCoordinates) {
        super(minCoordinates, maxCoordinates);
    }

    public static LargeArea of(Coordinates minCoordinates) {
        return new LargeArea(minCoordinates, Coordinates.of(Longitude.INCLUSIVE_MAX_VALUE, Latitude.INCLUSIVE_MAX_VALUE));
    }
}

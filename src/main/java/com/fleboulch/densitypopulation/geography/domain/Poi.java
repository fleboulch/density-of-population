package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.Objects;

public class Poi {

    private final String id;
    private final Coordinates coordinates;

    public Poi(String id, Coordinates coordinates) {
        this.id = Domain.validateNotNull(id, "Id should not be null for a Poi");
        this.coordinates = Domain.validateNotNull(coordinates, "Coordinates should not be null for a Poi");
    }

    public String getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poi poi = (Poi) o;
        return id.equals(poi.id) &&
                coordinates.equals(poi.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coordinates);
    }

    @Override
    public String toString() {
        return "Poi{" +
                "id='" + id + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}

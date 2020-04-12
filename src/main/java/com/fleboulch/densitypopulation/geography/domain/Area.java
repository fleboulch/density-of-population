package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.Objects;

public class Area {

    private final Coordinates inclusiveMinCoordinates;
    private final Coordinates inclusiveMaxCoordinates;

    protected Area(Coordinates inclusiveMinCoordinates, Coordinates inclusiveMaxCoordinates) {
        this.inclusiveMinCoordinates = Domain.validateNotNull(inclusiveMinCoordinates, "An area should have a non null min coordinates");
        this.inclusiveMaxCoordinates = Domain.validateNotNull(inclusiveMaxCoordinates, "An area should have a non null max coordinates");;
    }

    public static Area of(Coordinates inclusiveMinCoordinates) {
        Domain.validateNotNull(inclusiveMinCoordinates, "An area should have a non null min coordinates");
        return new Area(inclusiveMinCoordinates, inclusiveMinCoordinates.increment());
    }

    public boolean contains(Poi poi) {
        return poi.getCoordinates().between(inclusiveMinCoordinates, inclusiveMaxCoordinates);
    }

    public Coordinates getInclusiveMinCoordinates() {
        return inclusiveMinCoordinates;
    }

    public Coordinates getInclusiveMaxCoordinates() {
        return inclusiveMaxCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return inclusiveMinCoordinates.equals(area.inclusiveMinCoordinates) &&
                inclusiveMaxCoordinates.equals(area.inclusiveMaxCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inclusiveMinCoordinates, inclusiveMaxCoordinates);
    }

    @Override
    public String toString() {
        return "Area{" +
                "inclusiveMinCoordinates=" + inclusiveMinCoordinates +
                ", inclusiveMaxCoordinates=" + inclusiveMaxCoordinates +
                '}';
    }

}

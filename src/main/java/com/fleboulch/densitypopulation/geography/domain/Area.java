package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.Objects;

public class Area {

    private final Coordinates minCoordinates;
    private final Coordinates maxCoordinates;

    protected Area(Coordinates minCoordinates, Coordinates maxCoordinates) {
        this.minCoordinates = Domain.validateNotNull(minCoordinates, "An area should have a non null min coordinates");
        this.maxCoordinates = Domain.validateNotNull(maxCoordinates, "An area should have a non null max coordinates");;
    }

    public static Area of(Coordinates minCoordinates) {
        Domain.validateNotNull(minCoordinates, "An area should have a non null min coordinates");
        return new Area(minCoordinates, minCoordinates.increment());
    }

    public Coordinates getMinCoordinates() {
        return minCoordinates;
    }

    public Coordinates getMaxCoordinates() {
        return maxCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return minCoordinates.equals(area.minCoordinates) &&
                maxCoordinates.equals(area.maxCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minCoordinates, maxCoordinates);
    }

    @Override
    public String toString() {
        return "Area{" +
                "minCoordinates=" + minCoordinates +
                ", maxCoordinates=" + maxCoordinates +
                '}';
    }
}

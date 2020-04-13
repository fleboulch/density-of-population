package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Coordinates {

    private final Longitude longitude;
    private final Latitude latitude;

    private Coordinates(Longitude longitude, Latitude latitude) {
        this.longitude = Domain.validateNotNull(longitude, "Longitude should not be null for a coordinates");
        this.latitude = Domain.validateNotNull(latitude, "Latitude should not be null for a coordinates");
    }

    public static Coordinates of(double longitude, double latitude) {
        return new Coordinates(new Longitude(longitude), new Latitude(latitude));
    }

    public Coordinates increment() {
        return new Coordinates(this.longitude.increment(), this.latitude.increment());
    }

    public boolean between(Coordinates inclusiveMinCoordinates, Coordinates inclusiveMaxCoordinates) {
        return longitude.between(inclusiveMinCoordinates.longitude, inclusiveMaxCoordinates.longitude)
                && latitude.between(inclusiveMinCoordinates.latitude, inclusiveMaxCoordinates.latitude);
    }

    public Set<Coordinates> nearest() {
        List<Longitude> nearestLongitudes = longitude.nearestLongitudes();
        List<Latitude> nearestLatitudes = latitude.nearestLatitudes();
        if (nearestLatitudes.size() >= 1 && nearestLongitudes.size() == 1) {
            return nearestLatitudes.stream()
                    .map(lat -> new Coordinates(nearestLongitudes.get(0), lat))
                    .collect(toSet());
        }
        if (nearestLongitudes.size() > 1 && nearestLatitudes.size() == 1) {
            return nearestLongitudes.stream()
                    .map(lon -> new Coordinates(lon, nearestLatitudes.get(0)))
                    .collect(toSet());
        }

        Set<Coordinates> firstCombination = nearestLatitudes.stream()
                .map(lat -> new Coordinates(nearestLongitudes.get(0), lat))
                .collect(toSet());
        Set<Coordinates> secondCombination = nearestLatitudes.stream()
                .map(lat -> new Coordinates(nearestLongitudes.get(1), lat))
                .collect(toSet());

        return Stream.concat(firstCombination.stream(), secondCombination.stream())
                .collect(Collectors.toSet());

    }

    public Longitude getLongitude() {
        return longitude;
    }

    public Latitude getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return longitude.equals(that.longitude) &&
                latitude.equals(that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

}

package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;
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

        return latitude.nearestLatitudes().stream()
                .flatMap(lat -> buildCoordinatesFromLatitude(longitude.nearestLongitudes(), lat))
                .collect(toSet());
    }

    private Stream<Coordinates> buildCoordinatesFromLatitude(List<Longitude> nearestLongitudes, Latitude lat) {
        return nearestLongitudes.stream()
                .map(lon -> Coordinates.of(lon.getValue(), lat.getValue()));
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

package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.Objects;

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

    public Coordinates increment() {
        return new Coordinates(this.longitude.increment(), this.latitude.increment());
    }
}

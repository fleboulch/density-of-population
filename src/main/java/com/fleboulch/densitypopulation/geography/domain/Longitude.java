package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Longitude extends Axis {

    public static final int INCLUSIVE_MIN_VALUE = -180;
    public static final int INCLUSIVE_MAX_VALUE = 180;

    public Longitude(double value) {
        super(value);
        Domain.validateAttributeBetween(INCLUSIVE_MIN_VALUE, INCLUSIVE_MAX_VALUE, value, "Longitude value should be valid");
    }

    public Set<Longitude> nearestLongitudes() {
        Set<Double> nearest = nearest();
        return nearest.stream()
                .map(Longitude::new)
                .collect(toSet());
    }

    @Override
    public String toString() {
        return "Longitude{" +
                "value=" + value +
                '}';
    }

    @Override
    public Longitude increment() {
        if (value == INCLUSIVE_MAX_VALUE) {
            return new Longitude(value);
        }
        return new Longitude(value + INCREMENT);
    }
}

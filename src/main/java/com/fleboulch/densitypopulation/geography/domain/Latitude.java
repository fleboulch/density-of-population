package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class Latitude extends Axis {

    public static final int INCLUSIVE_MIN_VALUE = -90;
    public static final int INCLUSIVE_MAX_VALUE = 90;

    public Latitude(double value) {
        super(value);
        Domain.validateAttributeBetween(INCLUSIVE_MIN_VALUE, INCLUSIVE_MAX_VALUE, value, "Latitude value should be valid");
    }

    public List<Latitude> nearestLatitudes() {
        Set<Double> nearest = nearest();
        return nearest.stream()
                .map(Latitude::new)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Latitude{" +
                "value=" + value +
                '}';
    }

    @Override
    public Latitude increment() {
        return new Latitude(increment(INCLUSIVE_MAX_VALUE));
    }

}

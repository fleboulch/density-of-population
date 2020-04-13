package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.Set;

public class Longitude extends Axis {

    public static final int INCLUSIVE_MIN_VALUE = -180;
    public static final int INCLUSIVE_MAX_VALUE = 180;

    public Longitude(double value) {
        super(value);
        Domain.validateAttributeBetween(INCLUSIVE_MIN_VALUE, INCLUSIVE_MAX_VALUE, value, "Longitude value should be valid");
    }

    public Set<Longitude> nearest() {
        double floor = Math.floor(value);
        if ((value - floor) != INCREMENT && (value - floor) != 0) {
            if (floor < 0) {
                return Set.of(new Longitude(floor + INCREMENT));
            }
            return Set.of(new Longitude(floor));

        }
        return Set.of(
                new Longitude(value - INCREMENT),
                new Longitude(value)
        );

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

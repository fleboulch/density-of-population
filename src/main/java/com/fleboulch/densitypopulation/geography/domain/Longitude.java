package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.Objects;

public class Longitude {

    private static final int INCLUSIVE_MIN_VALUE = -180;
    private static final int INCLUSIVE_MAX_VALUE = 180;

    private final double value;

    public Longitude(double value) {
        this.value = Domain.validateAttributeBetween(INCLUSIVE_MIN_VALUE, INCLUSIVE_MAX_VALUE, value, "Longitude value should be valid");
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Longitude longitude = (Longitude) o;
        return Double.compare(longitude.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Longitude{" +
                "value=" + value +
                '}';
    }
}

package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.Domain;

import java.util.Objects;

public class Latitude {

    private static final int INCLUSIVE_MIN_VALUE = -90;
    private static final int INCLUSIVE_MAX_VALUE = 90;

    private final double value;

    public Latitude(double value) {
        this.value = Domain.validateAttributeBetween(INCLUSIVE_MIN_VALUE, INCLUSIVE_MAX_VALUE, value, "Latitude value should be valid");
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Latitude latitude = (Latitude) o;
        return Double.compare(latitude.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Latitude{" +
                "value=" + value +
                '}';
    }
}

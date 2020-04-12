package com.fleboulch.densitypopulation.geography.domain;

import java.util.Objects;

public abstract class Axis {

    public static final double INCREMENT = 0.5;

    protected final double value;

    public Axis(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Axis axis = (Axis) o;
        return Double.compare(axis.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Axis{" +
                "value=" + value +
                '}';
    }

    public abstract Axis increment();

    public boolean between(Axis inclusiveMinValue, Axis inclusiveMaxValue) {
        return value >= inclusiveMinValue.value && value <= inclusiveMaxValue.value;
    }
}

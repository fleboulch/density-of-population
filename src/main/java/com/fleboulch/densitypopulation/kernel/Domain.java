package com.fleboulch.densitypopulation.kernel;

import com.fleboulch.densitypopulation.kernel.exception.InvalidValueException;

import java.util.Objects;

public class Domain {

    private Domain() {
    }

    public static double validateAttributeBetween(int inclusiveMinValue, int inclusiveMaxValue, double attribute, String message) {
        if (attribute < inclusiveMinValue || attribute > inclusiveMaxValue) {
            throw new InvalidValueException(inclusiveMinValue, inclusiveMaxValue, attribute, message);
        }
        return attribute;
    }

    public static <T> T validateNotNull(T attribute, String message) {
        if (Objects.isNull(attribute)) {
            throw new DomainException(message);
        }
        return attribute;
    }
}

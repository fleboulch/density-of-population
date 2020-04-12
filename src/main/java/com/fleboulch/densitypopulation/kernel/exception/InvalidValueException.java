package com.fleboulch.densitypopulation.kernel.exception;

public class InvalidValueException extends RuntimeException {

    public InvalidValueException(int inclusiveMinValue, int inclusiveMaxValue, double value, String message) {
        super(String.format("%s. Reason: value '%f' is not between [%d, %d]", message, value, inclusiveMinValue, inclusiveMaxValue));
    }
}

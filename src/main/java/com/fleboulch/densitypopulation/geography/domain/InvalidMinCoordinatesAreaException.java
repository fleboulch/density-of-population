package com.fleboulch.densitypopulation.geography.domain;

public class InvalidMinCoordinatesAreaException extends RuntimeException {

    public InvalidMinCoordinatesAreaException(Coordinates coordinates) {
        super(String.format("The min coordinates [{} - {}] for an area is invalid. It should be smaller than max - increment", coordinates.getLongitude(), coordinates.getLatitude()));
    }
}

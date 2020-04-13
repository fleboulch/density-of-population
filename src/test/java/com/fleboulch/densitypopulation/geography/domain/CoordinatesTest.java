package com.fleboulch.densitypopulation.geography.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinatesTest {

    @Test
    void two_coordinates_with_same_properties_should_be_equal() {
        Coordinates poi = Coordinates.of(180, 90);
        Coordinates poi2 = Coordinates.of(180, 90);

        assertThat(poi).isEqualTo(poi2);
    }

    @Test
    void increment_should_return_new_coordinates_with_updated_longitude_and_latitude() {
        Coordinates coordinates = Coordinates.of(10, 20);
        Coordinates incrementedCoordinates = coordinates.increment();

        assertThat(incrementedCoordinates).isEqualTo(Coordinates.of(10.5, 20.5));
    }

    @Test
    void increment_should_return_original_coordinates_when_max() {
        Coordinates coordinates = Coordinates.of(Longitude.INCLUSIVE_MAX_VALUE, Latitude.INCLUSIVE_MAX_VALUE);
        Coordinates incrementedCoordinates = coordinates.increment();

        assertThat(incrementedCoordinates).isEqualTo(coordinates);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10, 20, 10, 20, 10, 20",
            "0.5, 10, 1, 15, 1.5, 20",
            "-10, -30, -6.5,-25, -3, -20"
    })
    void it_should_be_between(
            double inclusiveMinLon,
            double inclusiveMinLat,
            double lon,
            double lat,
            double inclusiveMaxLon,
            double inclusiveMaxLat) {
        Coordinates coordinates = Coordinates.of(lon, lat);
        Coordinates inclusiveMinCoordinates = Coordinates.of(inclusiveMinLon, inclusiveMinLat);
        Coordinates inclusiveMaxCoordinates = Coordinates.of(inclusiveMaxLon, inclusiveMaxLat);

        boolean isBetween = coordinates.between(inclusiveMinCoordinates, inclusiveMaxCoordinates);

        assertThat(isBetween).isTrue();

    }

    @ParameterizedTest
    @CsvSource(value = {
            "10, 10, 11, 10, 10, 10",
            "10, 10, 10, 11, 10, 10",
            "10, 10, 10.5, 10.5, 10, 10",
    })
    void it_should_not_be_between(
            double inclusiveMinLon,
            double inclusiveMinLat,
            double lon,
            double lat,
            double inclusiveMaxLon,
            double inclusiveMaxLat) {
        Coordinates coordinates = Coordinates.of(lon, lat);
        Coordinates inclusiveMinCoordinates = Coordinates.of(inclusiveMinLon, inclusiveMinLat);
        Coordinates inclusiveMaxCoordinates = Coordinates.of(inclusiveMaxLon, inclusiveMaxLat);

        boolean isBetween = coordinates.between(inclusiveMinCoordinates, inclusiveMaxCoordinates);

        assertThat(isBetween).isFalse();

    }

    @Test
    void a_coordinates_not_in_a_border_should_have_only_one_nearest_coordinates() {
        Coordinates coordinates = Coordinates.of(0.4, 0.4);

        Set<Coordinates> nearest = coordinates.nearest();
        assertThat(nearest).containsExactly(Coordinates.of(0, 0));
    }

}

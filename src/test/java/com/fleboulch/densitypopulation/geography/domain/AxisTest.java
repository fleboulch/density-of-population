package com.fleboulch.densitypopulation.geography.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AxisTest {

    @ParameterizedTest
    @CsvSource(value = {
            "0, 0, 0",
            "-1, 0, 1",
            "-10.5, -10, 1",
            "40.5, 41.8, 45",
    })
    void longitude_should_be_between(double inclusiveMin, double longitudeToCompare, double inclusiveMax) {
        Longitude longitude = new Longitude(longitudeToCompare);
        boolean isBetween = longitude.between(new Longitude(inclusiveMin), new Longitude(inclusiveMax));

        assertThat(isBetween).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0, 1, 0",
            "-1, 7, 1",
            "-10.5, -20, 1",
            "40.5, 60.8, 45",
    })
    void longitude_should_not_be_between(double inclusiveMin, double longitudeToCompare, double inclusiveMax) {
        Longitude longitude = new Longitude(longitudeToCompare);
        boolean isBetween = longitude.between(new Longitude(inclusiveMin), new Longitude(inclusiveMax));

        assertThat(isBetween).isFalse();
    }

}

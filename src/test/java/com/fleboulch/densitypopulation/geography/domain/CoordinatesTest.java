package com.fleboulch.densitypopulation.geography.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinatesTest {

    @Test
    void two_coordinates_with_same_properties_should_be_equal() {
        Coordinates poi = Coordinates.of(180, 90);
        Coordinates poi2 = Coordinates.of(180, 90);

        assertThat(poi).isEqualTo(poi2);
    }

}

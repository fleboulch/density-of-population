package com.fleboulch.densitypopulation.geography.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LargeAreaTest {

    @Test
    void create_large_area_should_succeed() {
        Coordinates minCoordinates = Coordinates.of(0, 0);
        LargeArea largeArea = LargeArea.of(minCoordinates);

        assertThat(largeArea.getInclusiveMinCoordinates()).isEqualTo(minCoordinates);
        assertThat(largeArea.getInclusiveMaxCoordinates()).isEqualTo(Coordinates.of(Longitude.INCLUSIVE_MAX_VALUE, Latitude.INCLUSIVE_MAX_VALUE));
    }
}

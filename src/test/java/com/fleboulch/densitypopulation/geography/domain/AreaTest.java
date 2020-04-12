package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AreaTest {

    public static final Coordinates PARIS_MIN_COORDINATES = Coordinates.of(2, 48.5);
    public static final Coordinates PARIS_MAX_COORDINATES = Coordinates.of(2.5, 49);

    @Test
    void two_are_with_same_properties_should_be_equal() {
        Area parisArea = Area.of(PARIS_MIN_COORDINATES);
        Area parisArea2 = Area.of(PARIS_MIN_COORDINATES);

        assertThat(parisArea).isEqualTo(parisArea2);
    }

    @Test
    void area_should_have_valid_increment() {
        Area parisArea = Area.of(PARIS_MIN_COORDINATES);

        assertThat(parisArea.getMinCoordinates()).isEqualTo(PARIS_MIN_COORDINATES);
        assertThat(parisArea.getMaxCoordinates()).isEqualTo(PARIS_MAX_COORDINATES);
    }

    @Test
    void an_area_should_have_non_null_coordinates() {
        assertThatThrownBy(
                () -> Area.of(null)
        ).isInstanceOf(DomainException.class);
    }

}

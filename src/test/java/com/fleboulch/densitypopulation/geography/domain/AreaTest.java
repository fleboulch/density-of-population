package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AreaTest {

    @Test
    void two_are_with_same_properties_should_be_equal() {
        Area parisArea = Area.of(Coordinates.of(2, 48.5), Coordinates.of(2.5, 49));
        Area parisArea2 = Area.of(Coordinates.of(2, 48.5), Coordinates.of(2.5, 49));

        assertThat(parisArea).isEqualTo(parisArea2);
    }

    @Test
    void an_area_should_have_non_null_coordinates() {
        assertThatThrownBy(
                () -> Area.of(null, null)
        ).isInstanceOf(DomainException.class);
    }

    @Test
    void an_area_should_have_non_null_coordinates_when_creating_min_area() {
        assertThatThrownBy(
                () -> Area.ofMin(null)
        ).isInstanceOf(DomainException.class);
    }

}

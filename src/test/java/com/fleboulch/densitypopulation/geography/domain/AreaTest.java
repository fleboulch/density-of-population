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

        assertThat(parisArea.getInclusiveMinCoordinates()).isEqualTo(PARIS_MIN_COORDINATES);
        assertThat(parisArea.getInclusiveMaxCoordinates()).isEqualTo(PARIS_MAX_COORDINATES);
    }

    @Test
    void an_area_should_have_non_null_coordinates() {
        assertThatThrownBy(
                () -> Area.of(null)
        ).isInstanceOf(DomainException.class);
    }

    @Test
    void france_area_should_contain_paris_pois() {
        Area france = LargeArea.of(Coordinates.of(0, 0));
        Poi parisPoi1 = new Poi("id1", PARIS_MIN_COORDINATES);
        Poi parisPoi2 = new Poi("id2", PARIS_MAX_COORDINATES);
        boolean contains1 = france.contains(parisPoi1);
        boolean contains2 = france.contains(parisPoi2);

        assertThat(contains1).isTrue();
        assertThat(contains2).isTrue();

    }

}

package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PoiTest {

    public static final String VALID_ID = "id";
    public static final Coordinates VALID_COORDINATES = Coordinates.of(180, 90);

    @Test
    void two_pois_with_same_properties_should_be_equal() {
        Poi poi = new Poi(VALID_ID, VALID_COORDINATES);
        Poi poi2 = new Poi(VALID_ID, VALID_COORDINATES);

        assertThat(poi).isEqualTo(poi2);
    }

    @Test
    void a_poi_should_have_non_null_coordinates() {
        assertThatThrownBy(
                () -> new Poi(VALID_ID, null)
        ).isInstanceOf(DomainException.class);
    }

    @Test
    void a_poi_should_have_non_null_id() {
        assertThatThrownBy(
                () -> new Poi(null, VALID_COORDINATES)
        ).isInstanceOf(DomainException.class);
    }

    @Test
    void a_poi_should_have_non_null_id_and_coordinates() {
        assertThatThrownBy(
                () -> new Poi(null, null)
        ).isInstanceOf(DomainException.class);
    }
}

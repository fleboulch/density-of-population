package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.DomainException;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

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

    @Test
    void it_should_return_one_nearest_area_when_poi_is_not_in_a_border() {
        Poi poi = new Poi("not border", Coordinates.of(0.4, 0.4));
        Stream<Area> nearestAreas = poi.findNearestAreas();

        assertThat(nearestAreas).containsExactlyInAnyOrder(Area.of(Coordinates.of(0, 0)));
    }

    @Test
    void it_should_return_two_nearest_areas_when_poi_is_in_a_border() {
        Poi poi = new Poi("long border", Coordinates.of(11, 0.8));
        Stream<Area> nearestAreas = poi.findNearestAreas();

        assertThat(nearestAreas).containsExactlyInAnyOrder(
                Area.of(Coordinates.of(10.5, 0.5)),
                Area.of(Coordinates.of(11, 0.5))

        );
    }

    @Test
    void it_should_return_four_nearest_areas_when_poi_is_in_a_border() {
        Poi poi = new Poi("long and lat borders", Coordinates.of(11, 2));
        Stream<Area> nearestAreas = poi.findNearestAreas();

        assertThat(nearestAreas).containsExactlyInAnyOrder(
                Area.of(Coordinates.of(10.5, 1.5)),
                Area.of(Coordinates.of(10.5, 2)),
                Area.of(Coordinates.of(11, 1.5)),
                Area.of(Coordinates.of(11, 2))

        );

    }
}

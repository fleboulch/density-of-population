package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.exception.InvalidValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LongitudeTest {

    @Test
    void two_longitudes_with_same_values_should_be_equal() {
        Longitude longitude = new Longitude(0);
        Longitude longitude2 = new Longitude(0);

        assertThat(longitude).isEqualTo(longitude2);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -200, -180.5, 180.5, 230
    })
    void it_should_fail_to_create_longitude_with_invalid_value(double invalidValue) {
        assertThatThrownBy(
                () -> new Longitude(invalidValue)
        ).isInstanceOf(InvalidValueException.class);
    }

    @Test
    void increment_longitude_should_return_new_longitude() {
        Longitude longitude = new Longitude(0);
        Longitude incrementedLongitude = longitude.increment();

        assertThat(incrementedLongitude).isEqualTo(new Longitude(Axis.INCREMENT));
    }

    @Test
    void increment_longitude_should_return_same_longitude_when_max_is_reached() {
        Longitude longitude = new Longitude(Longitude.INCLUSIVE_MAX_VALUE);
        Longitude incrementedLongitude = longitude.increment();

        assertThat(incrementedLongitude).isEqualTo(new Longitude(Longitude.INCLUSIVE_MAX_VALUE));
    }

    @Test
    void longitude_with_no_border_should_return_only_one_longitude() {
        Longitude longitude = new Longitude(0.4);
        Set<Longitude> nearest = longitude.nearest();

        assertThat(nearest).containsExactlyInAnyOrder(new Longitude(0));
    }

    @Test
    void negative_longitude_with_no_border_should_return_only_one_longitude() {
        Longitude longitude = new Longitude(-12.4);
        Set<Longitude> nearest = longitude.nearest();

        assertThat(nearest).containsExactlyInAnyOrder(new Longitude(-12.5));
    }

    @Test
    void longitude_with_a_border_should_return_two_longitudes() {
        Longitude longitude = new Longitude(0.5);
        Set<Longitude> nearest = longitude.nearest();

        assertThat(nearest).containsExactlyInAnyOrder(new Longitude(0), new Longitude(0.5));
    }

    @Test
    void negative_longitude_with_a_border_should_return_two_longitudes() {
        Longitude longitude = new Longitude(-1.5);
        Set<Longitude> nearest = longitude.nearest();

        assertThat(nearest).containsExactlyInAnyOrder(new Longitude(-2), new Longitude(-1.5));
    }

    @Test
    void negative_no_decimal_longitude_with_a_border_should_return_two_longitudes() {
        Longitude longitude = new Longitude(-1.0);
        Set<Longitude> nearest = longitude.nearest();

        assertThat(nearest).containsExactlyInAnyOrder(new Longitude(-1), new Longitude(-1.5));
    }

    @Test
    void no_decimal_longitude_with_a_border_should_return_two_longitudes() {
        Longitude longitude = new Longitude(11.0);
        Set<Longitude> nearest = longitude.nearest();

        assertThat(nearest).containsExactlyInAnyOrder(new Longitude(10.5), new Longitude(11));
    }

    @Test
    void negative_close_to_upper_longitude_with_a_border_should_return_two_longitudes() {
        Longitude longitude = new Longitude(-5.8);
        Set<Longitude> nearest = longitude.nearest();

        assertThat(nearest).containsExactlyInAnyOrder(new Longitude(-6));
    }

    @Test
    void close_to_upper_longitude_with_a_border_should_return_two_longitudes() {
        Longitude longitude = new Longitude(22.9);
        Set<Longitude> nearest = longitude.nearest();

        assertThat(nearest).containsExactlyInAnyOrder(new Longitude(22.5));
    }

}

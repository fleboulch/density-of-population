package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.exception.InvalidValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LatitudeTest {

    @Test
    void two_latitudes_with_same_values_should_be_equal() {
        Latitude latitude = new Latitude(0);
        Latitude latitude2 = new Latitude(0);

        assertThat(latitude).isEqualTo(latitude2);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -200, -90.5, 90.5, 150
    })
    void it_should_fail_to_create_latitude_with_invalid_value(double invalidValue) {
        assertThatThrownBy(
                () -> new Latitude(invalidValue)
        ).isInstanceOf(InvalidValueException.class);
    }


    @Test
    void increment_latitude_should_return_new_incremented_latitude() {
        Latitude latitude = new Latitude(0);
        Latitude incrementedLatitude = latitude.increment();

        assertThat(incrementedLatitude).isEqualTo(new Latitude(Axis.INCREMENT));
    }

    @Test
    void increment_latitude_should_return_same_latitude_when_max_is_reached() {
        Latitude latitude = new Latitude(Latitude.INCLUSIVE_MAX_VALUE);
        Latitude incrementeLatitude = latitude.increment();

        assertThat(incrementeLatitude).isEqualTo(new Latitude(Latitude.INCLUSIVE_MAX_VALUE));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0.4, 0",
            "-12.4, -12.5",
            "-5.8, -6",
            "22.9, 22.5"
    })
    void latitude_with_no_border_should_return_only_one_latitude(double value, double expectedValue) {
        Latitude latitude = new Latitude(value);
        Set<Latitude> nearest = latitude.nearestLatitudes();

        assertThat(nearest).containsExactlyInAnyOrder(new Latitude(expectedValue));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0.5, 0, 0.5",
            "-1.5, -2, -1.5",
            "-1.0, -1.0, -1.5",
            "11.0, 10.5, 11.0"
    })
    void latitude_with_a_border_should_return_two_latitudes(double value, double expectedValue1, double expectedValue2) {
        Latitude latitude = new Latitude(value);
        Set<Latitude> nearest = latitude.nearestLatitudes();

        assertThat(nearest).containsExactlyInAnyOrder(new Latitude(expectedValue1), new Latitude(expectedValue2));
    }

}

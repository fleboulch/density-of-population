package com.fleboulch.densitypopulation.geography.domain;

import com.fleboulch.densitypopulation.kernel.exception.InvalidValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

}

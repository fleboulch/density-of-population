package com.fleboulch.densitypopulation.geography.exposition;

import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;
import com.fleboulch.densitypopulation.geography.domain.Latitude;
import com.fleboulch.densitypopulation.geography.domain.Longitude;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationFactoryTest {

    @Test
    void it_should_succeed_to_convert() {
        LargeArea largeArea = ApplicationFactory.toDomain(
                "{\"min_lat\": 6.5,\n" +
                        "\"min_lon\": -7}"
        );

        assertThat(largeArea.getInclusiveMinCoordinates()).isEqualTo(Coordinates.of(-7, 6.5));
        assertThat(largeArea.getInclusiveMaxCoordinates()).isEqualTo(
                Coordinates.of(Longitude.INCLUSIVE_MAX_VALUE, Latitude.INCLUSIVE_MAX_VALUE)
        );
    }

}

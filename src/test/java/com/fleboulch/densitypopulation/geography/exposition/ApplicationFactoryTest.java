package com.fleboulch.densitypopulation.geography.exposition;

import com.fleboulch.densitypopulation.geography.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

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

    @Test
    void it_should_succeed_to_convert_densest_input() {
        int nbArea = ApplicationFactory.toNbAreaDomain(
                "{\"n\":2}"
        );

        assertThat(nbArea).isEqualTo(2);
    }

    @Test
    void it_should_succeed_to_convert_to_responses() {
        Area area1 = Area.of(Coordinates.of(0, 0));
        Set<AreaResponse> responses = ApplicationFactory.toResponses(Set.of(area1));

        AreaResponse actualResponse = new ArrayList<>(responses).get(0);

        assertThat(actualResponse.getMinLat()).isEqualTo(0);
        assertThat(actualResponse.getMinLon()).isEqualTo(0);
        assertThat(actualResponse.getMaxLat()).isEqualTo(Axis.INCREMENT);
        assertThat(actualResponse.getMaxLon()).isEqualTo(Axis.INCREMENT);
    }

}

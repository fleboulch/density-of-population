package com.fleboulch.densitypopulation.geography.infra;

import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.kernel.Domain;
import com.fleboulch.densitypopulation.kernel.DomainException;
import com.fleboulch.densitypopulation.kernel.exception.InvalidValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InfraFactoryTest {

    @Test
    void when_poi_is_valid_it_should_be_converted_to_domain() {
        String row = "id1\t-48.6\t-37.7";
        Poi poi = InfraFactory.toDomain(row);

        assertThat(poi).isEqualTo(new Poi("id1", Coordinates.of(-37.7, -48.6)));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "id1\t100\t-40",
            "id1\t40\t200",
    })
    void when_poi_is_not_valid_it_should_fail_to_convert(String row) {
        assertThatThrownBy(
                () ->InfraFactory.toDomain(row)
        ).isInstanceOf(InvalidValueException.class);

    }

}

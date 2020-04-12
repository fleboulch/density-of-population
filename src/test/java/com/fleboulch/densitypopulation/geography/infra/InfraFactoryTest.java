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

        assertThat(poi).isEqualTo(new Poi("id1", Coordinates.of(-48.6, -37.7)));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "id1\t-40\t100",
            "id1\t200\t40",
    })
    void when_poi_is_not_valid_it_should_fail_to_convert(String row) {
        assertThatThrownBy(
                () ->InfraFactory.toDomain(row)
        ).isInstanceOf(InvalidValueException.class);

    }

}

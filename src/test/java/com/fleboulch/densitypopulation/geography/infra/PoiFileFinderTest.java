package com.fleboulch.densitypopulation.geography.infra;

import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PoiFileFinderTest {

    private PoiFileFinder finder;

    @BeforeEach
    void setUp() {
        finder = new PoiFileFinder();
    }

    @Test
    void it_should_find_pois_from_config_tsv_file() {
        Set<Poi> tsvRowsWithoutHeader = finder.find();

        assertThat(tsvRowsWithoutHeader).containsExactlyInAnyOrder(
                new Poi("id1", Coordinates.of(-37.7, -48.6))
        );
    }

}

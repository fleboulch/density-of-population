package com.fleboulch.densitypopulation.geography.infra.helper;

import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class FileConverterTest {

    @Test
    void it_should_convert_empty_file() {
        Set<Poi> tsvRowsWithoutHeader = FileConverter.toDomain("empty.tsv");

        assertThat(tsvRowsWithoutHeader).isEmpty();
    }

    @Test
    void it_should_convert_one_line_file() {
        Set<Poi> tsvRowsWithoutHeader = FileConverter.toDomain("config.tsv");

        assertThat(tsvRowsWithoutHeader).containsExactly(new Poi("id1", Coordinates.of(-37.7, -48.6)));
    }

    @Test
    void it_should_convert_sample_file() {
        Set<Poi> tsvRowsWithoutHeader = FileConverter.toDomain("sample.tsv");

        assertThat(tsvRowsWithoutHeader).containsExactlyInAnyOrder(
                new Poi("id1", Coordinates.of(-37.7, -48.6)),
                new Poi("id2", Coordinates.of(8.4, -27.1)),
                new Poi("id3", Coordinates.of(-6.9, 6.6)),
                new Poi("id4", Coordinates.of(38.3,-2.3)),
                new Poi("id5", Coordinates.of(-6.9, 6.8)),
                new Poi("id6", Coordinates.of(38.3, -2.5)),
                new Poi("id7", Coordinates.of(-0.1, 0.1)),
                new Poi("id8", Coordinates.of(38.1, -2.1))
        );
    }

    @Test
    void it_should_convert_file_with_duplicate_pois() {
        Set<Poi> tsvRowsWithoutHeader = FileConverter.toDomain("duplicate.tsv");

        assertThat(tsvRowsWithoutHeader).containsExactly(new Poi("id1", Coordinates.of(-37.7, -48.6)));
    }

}

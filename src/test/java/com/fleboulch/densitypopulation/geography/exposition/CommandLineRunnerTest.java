package com.fleboulch.densitypopulation.geography.exposition;

import com.fleboulch.densitypopulation.geography.application.GeographyAlgo;
import com.fleboulch.densitypopulation.geography.domain.Area;
import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandLineRunnerTest {

    @Mock
    private GeographyAlgo geographyAlgo;

    private CommandLineRunner commandLineRunner;

    @BeforeEach
    void setUp() {
        commandLineRunner = new CommandLineRunner(geographyAlgo);
    }

    @Test
    void it_should_succeed() {
        when(geographyAlgo.fetchPoiInside(any())).thenReturn(emptySet());
        String response = commandLineRunner.computeNbPoi("{\"min_lat\": 6.5,\n" +
                "\"min_lon\": -7}");

        assertThat(response).isEqualTo("{\"value\":0}");
    }

    @Test
    void it_should_succeed_with_multiple_pois() {
        when(geographyAlgo.fetchPoiInside(any())).thenReturn(buildPois());
        String response = commandLineRunner.computeNbPoi("{\"min_lat\": 6.5,\n" +
                "\"min_lon\": -7}");

        assertThat(response).isEqualTo("{\"value\":4}");
    }

    @Test
    void it_should_succeed_to_fetch_densest_areas() {
        when(geographyAlgo.fetchDensestArea(eq(1))).thenReturn(buildAreas());
        String response = commandLineRunner.fetchDensestAreas("{\"n\": 1}");

        assertThat(response).isEqualTo("[{\"min_lat\":6.5,\"max_lat\":7.0,\"min_lon\":-7.0,\"max_lon\":-6.5}]");
    }

    private Set<Area> buildAreas() {
        return Set.of(
                Area.of(Coordinates.of(-7.0, 6.5))
        );

    }

    private Set<Poi> buildPois() {
        return Set.of(
                new Poi("id1", Coordinates.of(0, 0)),
                new Poi("id2", Coordinates.of(1, 0)),
                new Poi("id3", Coordinates.of(2, 0)),
                new Poi("id4", Coordinates.of(3, 0))
        );
    }
}

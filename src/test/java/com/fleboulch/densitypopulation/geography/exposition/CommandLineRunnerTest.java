package com.fleboulch.densitypopulation.geography.exposition;

import com.fleboulch.densitypopulation.geography.application.GeographyAlgo;
import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
        when(geographyAlgo.fetchPoiInside(any())).thenReturn(emptyList());
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

    private List<Poi> buildPois() {
        Poi poi = new Poi("id", Coordinates.of(0, 0));
        return List.of(poi, poi, poi, poi);
    }
}

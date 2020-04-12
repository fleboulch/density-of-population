package com.fleboulch.densitypopulation.geography.application;

import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.geography.infra.PoiFileFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeographyAlgoTest {

    @Mock
    private PoiFileFinder poiFileFinder;

    private GeographyAlgo geographyAlgo;

    @BeforeEach
    public void setup() {
        geographyAlgo = new GeographyAlgo(poiFileFinder);
    }

    @Test
    void when_poi_is_not_inside_area_it_should_return_an_empty_list() {
        when(poiFileFinder.find()).thenReturn(List.of(new Poi("id1", Coordinates.of(-1, -1))));

        List<Poi> pois = geographyAlgo.fetchPoiInside(LargeArea.of(Coordinates.of(0, 0)));

        assertThat(pois).isEmpty();
    }

    @Test
    void when_poi_is_inside_area_it_should_return_the_poi() {
        Poi poi1 = new Poi("id1", Coordinates.of(0, 0));
        when(poiFileFinder.find()).thenReturn(List.of(poi1));

        List<Poi> pois = geographyAlgo.fetchPoiInside(LargeArea.of(Coordinates.of(0, 0)));

        assertThat(pois).containsExactly(poi1);
    }
}

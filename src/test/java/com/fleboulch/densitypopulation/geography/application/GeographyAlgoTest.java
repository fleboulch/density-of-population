package com.fleboulch.densitypopulation.geography.application;

import com.fleboulch.densitypopulation.geography.domain.Area;
import com.fleboulch.densitypopulation.geography.domain.Coordinates;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;
import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.geography.infra.PoiFileFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

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
        when(poiFileFinder.find()).thenReturn(Set.of(buildPoi(-1)));

        Set<Poi> pois = geographyAlgo.fetchPoiInside(LargeArea.of(Coordinates.of(0, 0)));

        assertThat(pois).isEmpty();
    }

    @Test
    void when_poi_is_inside_area_it_should_return_the_poi() {
        Poi poi1 = buildPoi(0);
        when(poiFileFinder.find()).thenReturn(Set.of(poi1));

        Set<Poi> pois = geographyAlgo.fetchPoiInside(LargeArea.of(Coordinates.of(0, 0)));

        assertThat(pois).containsExactly(poi1);
    }

    @Test
    void it_should_return_the_densest_area() {
        Poi poi1 = buildPoi(0.2);
        when(poiFileFinder.find()).thenReturn(Set.of(poi1));
        Set<Area> densestAreas = geographyAlgo.fetchDensestArea(1);

        assertThat(densestAreas).containsExactly(Area.of(Coordinates.of(0, 0)));
    }

    @Test
    void it_should_return_the_densest_area_for_multiple_pois() {
        Poi poi1 = buildPoi(0.3, 0.4);
        Poi poi2 = buildPoi(0.2, 0.3);
        Poi poi3 = buildPoi(2.1, 3.9);
        when(poiFileFinder.find()).thenReturn(Set.of(poi1, poi2, poi3));
        Set<Area> densestAreas = geographyAlgo.fetchDensestArea(1);

        assertThat(densestAreas).containsExactly(Area.of(Coordinates.of(0, 0)));
    }

    @Test
    void it_should_return_the_densest_area_for_three_pois() {
        Poi poi1 = buildPoi(0.3, 0.4);
        Poi poi2 = buildPoi(2.2, 3.6);
        Poi poi3 = buildPoi(2.1, 3.9);
        when(poiFileFinder.find()).thenReturn(Set.of(poi1, poi2, poi3));
        Set<Area> densestAreas = geographyAlgo.fetchDensestArea(1);

        assertThat(densestAreas).containsExactly(Area.of(Coordinates.of(2, 3.5)));
    }

    @Test
    void it_should_return_the_two_densest_area_for_three_pois() {
        Poi poi1 = buildPoi(0.3, 0.4);
        Poi poi2 = buildPoi(2.2, 3.6);
        Poi poi3 = buildPoi(2.1, 3.9);
        when(poiFileFinder.find()).thenReturn(Set.of(poi1, poi2, poi3));
        Set<Area> densestAreas = geographyAlgo.fetchDensestArea(2);

        assertThat(densestAreas).containsExactly(
                Area.of(Coordinates.of(2, 3.5)),
                Area.of(Coordinates.of(0, 0))
        );
    }

    private Poi buildPoi(double longitude, double latitude) {
        return new Poi("id1", Coordinates.of(longitude, latitude));
    }

    private Poi buildPoi(double coordinates) {
        return new Poi("id1", Coordinates.of(coordinates, coordinates));
    }

}

package com.fleboulch.densitypopulation.geography.exposition;

import com.fleboulch.densitypopulation.geography.application.GeographyAlgo;
import com.fleboulch.densitypopulation.geography.domain.LargeArea;

public class CommandLineRunner {

    private final GeographyAlgo geographyAlgo;

    public CommandLineRunner(GeographyAlgo geographyAlgo) {
        this.geographyAlgo = geographyAlgo;
    }

    public int computeNbPoi(String inputArea) {
        LargeArea largeArea = ApplicationFactory.toDomain(inputArea);
        return geographyAlgo.fetchPoiInside(largeArea).size();
    }
}

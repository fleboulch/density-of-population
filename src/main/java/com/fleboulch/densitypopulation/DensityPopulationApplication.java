package com.fleboulch.densitypopulation;

import com.fleboulch.densitypopulation.geography.application.GeographyAlgo;
import com.fleboulch.densitypopulation.geography.exposition.CommandLineRunner;
import com.fleboulch.densitypopulation.geography.infra.PoiFileFinder;

import java.util.Objects;

public class DensityPopulationApplication {

    public static void main(String[] args) {
        CommandLineRunner commandLineRunner = new CommandLineRunner(new GeographyAlgo(new PoiFileFinder()));
        String type = args[0].trim();
        String input = args[1].trim();

        if (Objects.equals(type, "--nbpoi")) {
            String json = commandLineRunner.computeNbPoi(input);
            System.out.println(json);
        } else if (Objects.equals(type, "--densest")) {
            String json = commandLineRunner.fetchDensestAreas(input);
            System.out.println(json);
        }

    }

}

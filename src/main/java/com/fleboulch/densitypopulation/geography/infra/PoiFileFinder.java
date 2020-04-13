package com.fleboulch.densitypopulation.geography.infra;

import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.geography.infra.helper.FileConverter;

import java.util.Set;

public class PoiFileFinder {

    public static final String FILE_PATH = "config.tsv";

    public Set<Poi> find() {
        return FileConverter.toDomain(FILE_PATH);
    }

}

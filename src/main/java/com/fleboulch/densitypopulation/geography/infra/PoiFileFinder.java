package com.fleboulch.densitypopulation.geography.infra;

import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.geography.infra.helper.FileConverter;

import java.util.List;

public class PoiFileFinder {

    public static final String FILE_PATH = "config.tsv";

    public List<Poi> find() {
        return FileConverter.toDomain(FILE_PATH);
    }

}

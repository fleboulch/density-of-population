package com.fleboulch.densitypopulation.geography.infra.helper;

import com.fleboulch.densitypopulation.geography.domain.Poi;
import com.fleboulch.densitypopulation.geography.infra.InfraFactory;

import java.io.*;
import java.net.URL;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

public class FileConverter {

    private FileConverter() {
    }

    public static Set<Poi> toDomain(String filePath) {
        File file = getFileFromResources(filePath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

            return br.lines()
                    .skip(1) // skip header
                    .map(InfraFactory::toDomain)
                    .collect(toSet());

        } catch (IOException e) {
            throw new RuntimeException("IO exception", e);
        }
    }

    private static File getFileFromResources(String filePath) {

        ClassLoader classLoader = FileConverter.class.getClassLoader();

        URL resource = classLoader.getResource(filePath);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }

}

[![codecov](https://codecov.io/gh/fleboulch/density-of-population/branch/master/graph/badge.svg)](https://codecov.io/gh/fleboulch/density-of-population)

# Density of population

## Prerequisites

- JDK 13

## Build project

```
./gradlew build
```
## Run tests

```
./gradlew test
```

## Run the app

Run the app from gradle command line with JSON content seems to be quite challenging.  
This [github issue](https://github.com/gradle/gradle/issues/865) has been added in 2016 and seems to be fixed in v3.3 but I didn't succeed to run the app from CLI with JSON arg.  
Broken command example: `./gradlew run --args='--densest {\"n\":2}'`

Note: I mainly used unit tests during development

### Compute the number of POIs in an area

You can run this directly in your IDE with program arguments: `--nbpoi {\"min_lat\":6.5,\"min_lon\":-7}'`

### Fetch the densest areas

You can run this directly in your IDE with program arguments: `--densest {\"n\":2}`

## POI implementation

In my app, 2 POIs should be equal if they have same id and coordinates.
I suppose 2 POIs should have the exactly same latitude and longitude.

## TSV file location

When you run the project the POIs are defined in a tsv file located in `src/main/resources/config.tsv`

## Code coverage

Code coverage metrics from Codecov and IntelliJ are not the same.  
Codecov: 81% vs IntelliJ: 88%

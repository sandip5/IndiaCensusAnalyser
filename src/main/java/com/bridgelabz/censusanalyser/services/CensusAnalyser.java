package com.bridgelabz.censusanalyser.services;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.model.UsCensusCSV;
import com.bridgelabz.censusanalyser.utility.CSVBuilderFactory;
import com.bridgelabz.censusanalyser.utility.ICSVBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

import static com.bridgelabz.censusanalyser.exception.CensusAnalyserException.ExceptionType;

public class CensusAnalyser {
    Collection<Object> censusRecords = null;
    HashMap<Object, Object> censusHashMap = null;

    /**
     * Loading Census Data By Passing Path Of CSV File
     *
     * @param csvFilePath
     * @return Size Of Census CSV List
     * @throws CensusAnalyserException
     */
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusHashMap = csvBuilder.getCSVFileMap(reader, IndiaCensusCSV.class);
            return censusHashMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Loading State Code By Passing Path Of CSV File
     *
     * @param indiaCensusCSVFilePath
     * @return Size Of State Code CSV List
     * @throws CensusAnalyserException
     */
    public int loadIndiaStateCode(String indiaCensusCSVFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCSVFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusHashMap = csvBuilder.getCSVFileMap(reader, IndiaStateCodeCSV.class);
            return censusHashMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusHashMap = csvBuilder.getCSVFileMap(reader, UsCensusCSV.class);
            return censusHashMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * State Wise Sorting From India Census CSV File
     *
     * @return sortedStateCensusJson
     * @throws CensusAnalyserException
     */
    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        checkMap();
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.asscendingSort(censusComparator, censusHashMap);
        censusRecords = censusHashMap.values();
        String sortedStateCensusJson = new Gson().toJson(censusRecords);
        return sortedStateCensusJson;
    }

    /**
     * Population Wise Sorting From India Census CSV File
     *
     * @return sortedPopulationCensusJson
     * @throws CensusAnalyserException
     */
    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        checkMap();
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.population);
        this.asscendingSort(censusComparator, censusHashMap);
        censusRecords = censusHashMap.values();
        String sortedPopulationCensusJson = new Gson().toJson(censusRecords);
        String fileName = "./src/test/resources/PopulationWiseSortedIndiaCensus.json";
        this.writeIntoJson(fileName);
        return sortedPopulationCensusJson;
    }

    /**
     * Sorting Least To Largest Order For India Census CSV
     *
     * @param censusComparator
     */
    private <E> void asscendingSort(Comparator<E> censusComparator, Map<Object, Object> censusRecords) {
        IntStream.range(0, censusRecords.size()).flatMap(rowCounter -> IntStream
                .range(0, censusRecords.size() - rowCounter - 1)).forEach(columnCounter -> {
            E census1 = (E) censusRecords.get(columnCounter);
            E census2 = (E) censusRecords.get(columnCounter + 1);
            if (censusComparator.compare(census1, census2) > 0) {
                censusRecords.put(columnCounter, census2);
                censusRecords.put(columnCounter + 1, census1);
            }
        });
    }

    /**
     * State Wise Sorting From India State Code CSV File
     *
     * @return sortedStateCodeJson
     * @throws CensusAnalyserException
     */
    public String getStateWiseSortedStateCodeCSV() throws CensusAnalyserException {
        checkMap();
        Comparator<IndiaStateCodeCSV> stateCodeComparator = Comparator.comparing(census -> census.stateCode);
        this.asscendingSort(stateCodeComparator, censusHashMap);
        censusRecords = censusHashMap.values();
        String sortedStateCodeJson = new Gson().toJson(censusRecords);
        return sortedStateCodeJson;
    }

    /**
     * Density Wise Sorting From India State Code CSV File
     *
     * @return sortedPopulationDensityCensusJson
     * @throws CensusAnalyserException
     */
    public String getPopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
        checkMap();
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.descendingSort(censusComparator, censusHashMap);
        censusRecords = censusHashMap.values();
        String sortedPopulationDensityCensusJson = new Gson().toJson(censusRecords);
        String fileName = "./src/test/resources/PopulationDensityWiseSortedIndiaCensus.json";
        this.writeIntoJson(fileName);
        return sortedPopulationDensityCensusJson;
    }

    /**
     * Write List Object Into Json Object
     *
     * @param fileName
     */
    private void writeIntoJson(String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusHashMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Area Wise Sorting From India State Code CSV File
     *
     * @return sortedAreaCensusJson
     * @throws CensusAnalyserException
     */
    public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
        checkMap();
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        this.descendingSort(censusComparator, censusHashMap);
        censusRecords = censusHashMap.values();
        String sortedAreaCensusJson = new Gson().toJson(censusRecords);
        String fileName = "./src/test/resources/AreaWiseSortedIndiaCensus.json";
        this.writeIntoJson(fileName);
        return sortedAreaCensusJson;
    }

    /**
     * Sorting Largest to Least Order For India Census CSV
     *
     * @param censusComparator
     */
    private <E> void descendingSort(Comparator<E> censusComparator, Map<Object, Object> censusRecords) {
        IntStream.range(0, censusRecords.size()).map(rowCounter -> censusRecords.size() - rowCounter - 1)
                .forEach(bound -> IntStream.range(0, bound).forEach(columnCounter -> {
                    E census1 = (E) censusRecords.get(columnCounter);
                    E census2 = (E) censusRecords.get(columnCounter + 1);
                    if (censusComparator.compare(census1, census2) < 0) {
                        censusRecords.put(columnCounter, census2);
                        censusRecords.put(columnCounter + 1, census1);
                    }
                }));
    }

    /**
     * Sorting Population Wise From US Census Data
     *
     * @return sortedPopulationUSCensusJson
     * @throws CensusAnalyserException
     */
    public String getPopulationWiseSortedUSCensusData() throws CensusAnalyserException {
        checkMap();
        Comparator<UsCensusCSV> censusComparator = Comparator.comparing(census -> census.usPopulation);
        this.descendingSort(censusComparator, censusHashMap);
        censusRecords = censusHashMap.values();
        String sortedPopulationUSCensusJson = new Gson().toJson(censusRecords);
        String fileName = "./src/test/resources/PopulationWiseSortedUSCensus.json";
        this.writeIntoJson(fileName);
        return sortedPopulationUSCensusJson;
    }

    /**
     * Sorting Housing Units Wise From US Census Data
     *
     * @return sortedHousingUnitsUSCensusJson
     * @throws CensusAnalyserException
     */
    public String getHousingUnitWiseSortedUSCensusData() throws CensusAnalyserException {
        checkMap();
        Comparator<UsCensusCSV> censusComparator = Comparator.comparing(census -> census.housingUnits);
        this.descendingSort(censusComparator, censusHashMap);
        censusRecords = censusHashMap.values();
        String sortedHousingUnitsUSCensusJson = new Gson().toJson(censusRecords);
        String fileName = "./src/test/resources/HousingUnitWiseSortedUSCensus.json";
        this.writeIntoJson(fileName);
        return sortedHousingUnitsUSCensusJson;
    }

    private void checkMap() throws CensusAnalyserException {
        if (censusHashMap == null || censusHashMap.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
    }
}
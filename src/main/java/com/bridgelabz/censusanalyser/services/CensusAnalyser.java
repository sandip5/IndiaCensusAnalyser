package com.bridgelabz.censusanalyser.services;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaCensusDAO;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static com.bridgelabz.censusanalyser.exception.CensusAnalyserException.ExceptionType;

public class CensusAnalyser {
    List<IndiaCensusDAO> censusList = null;
    public CensusAnalyser(){
        this.censusList = new ArrayList<IndiaCensusDAO>();
    }

    /**
     * Loading Census Data By Passing Path Of CSV File
     * @param csvFilePath
     * @return Size Of Census CSV List
     * @throws CensusAnalyserException
     */
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            while (csvFileIterator.hasNext()){
                this.censusList.add(new IndiaCensusDAO(csvFileIterator.next()));
            }
            return this.censusList.size();
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
     * @param indiaCensusCSVFilePath
     * @return Size Of State Code CSV List
     * @throws CensusAnalyserException
     */
    public int loadIndiaStateCode(String indiaCensusCSVFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCSVFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            while (csvFileIterator.hasNext()){
                this.censusList.add(new IndiaCensusDAO(csvFileIterator.next()));
            }
            return this.censusList.size();
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
     * @return sortedStateCensusJson
     * @throws CensusAnalyserException
     */
    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        this.asscendingSort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(this.censusList);
        return sortedStateCensusJson;
    }

    /**
     * Population Wise Sorting From India Census CSV File
     * @return sortedPopulationCensusJson
     * @throws CensusAnalyserException
     */
    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0)
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        this.asscendingSort(censusComparator);
        String sortedPopulationCensusJson = new Gson().toJson(this.censusList);
        String fileName = "./src/test/resources/PopulationWiseSortedIndiaCensus.json";
        this.writeIntoJson(fileName);
        return sortedPopulationCensusJson;
    }

    /**
     * Sorting Least To Largest Order For India Census CSV
     * @param censusComparator
     */
    private void asscendingSort(Comparator<IndiaCensusDAO> censusComparator) {
        IntStream.range(0, censusList.size()).flatMap(rowCounter -> IntStream
                .range(0, censusList.size() - rowCounter - 1)).forEach(columnCounter -> {
            IndiaCensusDAO census1 = censusList.get(columnCounter);
            IndiaCensusDAO census2 = censusList.get(columnCounter + 1);
            if (censusComparator.compare(census1, census2) > 0) {
                censusList.set(columnCounter, census2);
                censusList.set(columnCounter + 1, census1);
            }
        });
    }

    /**
     * State Wise Sorting From India State Code CSV File
     * @return sortedStateCodeJson
     * @throws CensusAnalyserException
     */
    public String getStateWiseSortedStateCodeCSV() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0)
            throw new CensusAnalyserException("NO_CENSUS_DATA", ExceptionType.NO_CENSUS_DATA);
        Comparator<IndiaCensusDAO> stateCodeComparator = Comparator.comparing(census -> census.stateCode);
        this.asscendingSort(stateCodeComparator);
        String sortedStateCodeJson = new Gson().toJson(this.censusList);
        return sortedStateCodeJson;
    }

    /**
     * Density Wise Sorting From India State Code CSV File
     * @return sortedPopulationDensityCensusJson
     * @throws CensusAnalyserException
     */
    public String getPopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.descendingSort(censusComparator);
        String sortedPopulationDensityCensusJson = new Gson().toJson(this.censusList);
        String fileName = "./src/test/resources/PopulationDensityWiseSortedIndiaCensus.json";
        this.writeIntoJson(fileName);
        return sortedPopulationDensityCensusJson;
    }

    /**
     * Write List Object Into Json Object
     * @param fileName
     */
    private void writeIntoJson(String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Area Wise Sorting From India State Code CSV File
     * @return sortedAreaCensusJson
     * @throws CensusAnalyserException
     */
    public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0)
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        Comparator<IndiaCensusDAO> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        this.descendingSort(censusComparator);
        String sortedAreaCensusJson = new Gson().toJson(this.censusList);
        String fileName = "./src/test/resources/AreaWiseSortedIndiaCensus.json";
        this.writeIntoJson(fileName);
        return sortedAreaCensusJson;
    }

    /**
     * Sorting Largest to Least Order For India Census CSV
     * @param censusComparator
     */
    private void descendingSort(Comparator<IndiaCensusDAO> censusComparator) {
        IntStream.range(0, censusList.size()).map(rowCounter -> censusList.size() - rowCounter - 1)
                .forEach(bound -> IntStream.range(0, bound).forEach(columnCounter -> {
            IndiaCensusDAO census1 = censusList.get(columnCounter);
            IndiaCensusDAO census2 = censusList.get(columnCounter + 1);
            if (censusComparator.compare(census1, census2) < 0) {
                censusList.set(columnCounter, census2);
                censusList.set(columnCounter + 1, census1);
            }
        }));
    }
}
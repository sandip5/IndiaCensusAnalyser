package com.bridgelabz.censusanalyser.services;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import static com.bridgelabz.censusanalyser.exception.CensusAnalyserException.ExceptionType;

public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVList = null;
    List<IndiaStateCodeCSV> stateCodeCSVList = null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            censusCSVList = csvBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();
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

    public int loadIndiaStateCode(String indiaCensusCSVFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCSVFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            stateCodeCSVList = csvBuilder.getCSVFileList(reader, IndiaStateCodeCSV.class);
            return stateCodeCSVList.size();
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

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.indiaCensusSort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusCSVList);
        return sortedStateCensusJson;
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0)
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.population);
        this.indiaCensusSort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusCSVList);
        String fileName = "C:\\Users\\aple\\IdeaProjects\\IndianCensusAnalyser\\src\\test\\resources\\PopulationWiseSortedIndiaCensus.json";
        this.writeIntoJson(fileName);
        return sortedStateCensusJson;
    }

    private void indiaCensusSort(Comparator<IndiaCensusCSV> censusComparator) {
        for (int rowCounter = 0; rowCounter < censusCSVList.size(); rowCounter++) {
            for (int columnCounter = 0; columnCounter < censusCSVList.size() - rowCounter - 1; columnCounter++) {
                IndiaCensusCSV census1 = censusCSVList.get(columnCounter);
                IndiaCensusCSV census2 = censusCSVList.get(columnCounter + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusCSVList.set(columnCounter, census2);
                    censusCSVList.set(columnCounter + 1, census1);
                }
            }
        }
    }

    public String getStateWiseSortedStateCodeCSV() throws CensusAnalyserException {
        if (stateCodeCSVList == null || stateCodeCSVList.size() == 0)
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        Comparator<IndiaStateCodeCSV> stateCodeComparator = Comparator.comparing(census -> census.stateCode);
        this.stateCodeSort(stateCodeComparator);
        String sortedStateCodeJson = new Gson().toJson(stateCodeCSVList);
        return sortedStateCodeJson;
    }

    private void stateCodeSort(Comparator<IndiaStateCodeCSV> stateCodeComparator) {
        for (int rowCounter = 0; rowCounter < stateCodeCSVList.size(); rowCounter++) {
            for (int columnCounter = 0; columnCounter < stateCodeCSVList.size() - rowCounter - 1; columnCounter++) {
                IndiaStateCodeCSV census1 = stateCodeCSVList.get(columnCounter);
                IndiaStateCodeCSV census2 = stateCodeCSVList.get(columnCounter + 1);
                if (stateCodeComparator.compare(census1, census2) > 0) {
                    stateCodeCSVList.set(columnCounter, census2);
                    stateCodeCSVList.set(columnCounter + 1, census1);
                }
            }
        }
    }

    public String getPopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.indiaCensusLargestToLeastSort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusCSVList);
        String fileName = "C:\\Users\\aple\\IdeaProjects\\IndianCensusAnalyser\\src\\test\\resources\\PopulationDensityWiseSortedIndiaCensus.json";
        this.writeIntoJson(fileName);
        return sortedStateCensusJson;
    }

    private void writeIntoJson(String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusCSVList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0)
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        this.indiaCensusLargestToLeastSort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusCSVList);
        String fileName = "C:\\Users\\aple\\IdeaProjects\\IndianCensusAnalyser\\src\\test\\resources\\AreaWiseSortedIndiaCensus.json";
        this.writeIntoJson(fileName);
        return sortedStateCensusJson;
    }

    private void indiaCensusLargestToLeastSort(Comparator<IndiaCensusCSV> censusComparator) {
        for (int rowCounter = 0; rowCounter < censusCSVList.size(); rowCounter++) {
            for (int columnCounter = 0; columnCounter < censusCSVList.size() - rowCounter - 1; columnCounter++) {
                IndiaCensusCSV census1 = censusCSVList.get(columnCounter);
                IndiaCensusCSV census2 = censusCSVList.get(columnCounter + 1);
                if (censusComparator.compare(census1, census2) < 0) {
                    censusCSVList.set(columnCounter, census2);
                    censusCSVList.set(columnCounter + 1, census1);
                }
            }
        }
    }

}
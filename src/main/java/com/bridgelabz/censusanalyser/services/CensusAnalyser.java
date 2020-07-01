package com.bridgelabz.censusanalyser.services;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.bridgelabz.censusanalyser.exception.CensusAnalyserException.ExceptionType;

public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVList = null;
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
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
            Iterator<IndiaStateCodeCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            return this.getCount(censusCSVIterator);
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

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int numberOfEntry = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return numberOfEntry;
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusCSVList);
        return sortedStateCensusJson;
    }
    private void sort(Comparator<IndiaCensusCSV> censusComparator) {
        for (int i = 0; i < censusCSVList.size(); i++) {
            for (int j = 0; j < censusCSVList.size() - i - 1; j++) {
                IndiaCensusCSV census1 = censusCSVList.get(j);
                IndiaCensusCSV census2 = censusCSVList.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusCSVList.set(j, census2);
                    censusCSVList.set(j + 1, census1);
                }
            }
        }
    }

}
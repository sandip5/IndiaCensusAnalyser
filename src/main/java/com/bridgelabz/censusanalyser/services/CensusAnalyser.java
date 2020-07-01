package com.bridgelabz.censusanalyser.services;

import OpenCSVBuilder_JAR.CSVBuilderException;
import OpenCSVBuilder_JAR.CSVBuilderFactory;
import OpenCSVBuilder_JAR.ICSVBuilder;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import static com.bridgelabz.censusanalyser.exception.CensusAnalyserException.ExceptionType;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            return this.getCount(censusCSVIterator);
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
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
        }
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int numberOfEntry = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return numberOfEntry;
    }
}
package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.dto.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.dto.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.dto.UsCensusCSV;
import com.bridgelabz.censusanalyser.exception.CSVBuilderException;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.utility.CSVBuilderFactory;
import com.bridgelabz.censusanalyser.utility.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CensusAdapter {
    public abstract List loadCensusData(String... csvFilePath) throws CensusAnalyserException;

    public static List<CensusDAO> censusList = new ArrayList<>();

    /**
     * Loan Census Analyser CSV File
     *
     * @param censusClass
     * @param csvFilePath
     * @param <E>
     * @return
     * @throws CensusAnalyserException
     */
    public <E> List censusLoader(Class<E> censusClass, String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, censusClass);
            switch (censusClass.getSimpleName()) {
                case "IndiaCensusCSV":
                    censusList.clear();
                    while (csvFileIterator.hasNext()) {
                        this.censusList.add(new CensusDAO((IndiaCensusCSV) csvFileIterator.next()));
                    }
                    break;
                case "IndiaStateCodeCSV":
                    censusList.clear();
                    while (csvFileIterator.hasNext()) {
                        this.censusList.add(new CensusDAO((IndiaStateCodeCSV) csvFileIterator.next()));
                    }
                    break;
                case "UsCensusCSV":
                    censusList.clear();
                    while (csvFileIterator.hasNext()) {
                        this.censusList.add(new CensusDAO((UsCensusCSV) csvFileIterator.next()));
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + censusClass.getSimpleName());
            }
            return this.censusList;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.DELIMITER_HEADER_PROBLEM);
        }
    }
}

package com.bridgelabz.censusanalyser.utility;

import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;
import java.util.stream.IntStream;

import static com.bridgelabz.censusanalyser.services.CensusLoader.censusList;

public class Utility {
    /**
     * Sorting Least To Largest Order For India Census CSV
     * @param censusComparator
     */
    public void ascendingSort(Comparator<CensusDAO> censusComparator) {
        IntStream.range(0, censusList.size()).flatMap(rowCounter -> IntStream
                .range(0, censusList.size() - rowCounter - 1)).forEach(columnCounter -> {
            CensusDAO census1 = censusList.get(columnCounter);
            CensusDAO census2 = censusList.get(columnCounter + 1);
            if (censusComparator.compare(census1, census2) > 0) {
                censusList.set(columnCounter, census2);
                censusList.set(columnCounter + 1, census1);
            }
        });
    }

    /**
     * Sorting Largest to Least Order For India Census CSV
     *
     * @param censusComparator
     */
    public void descendingSort(Comparator<CensusDAO> censusComparator) {
        IntStream.range(0, censusList.size()).map(rowCounter -> censusList.size() - rowCounter - 1)
                .forEach(bound -> IntStream.range(0, bound).forEach(columnCounter -> {
                    CensusDAO census1 = censusList.get(columnCounter);
                    CensusDAO census2 = censusList.get(columnCounter + 1);
                    if (censusComparator.compare(census1, census2) < 0) {
                        censusList.set(columnCounter, census2);
                        censusList.set(columnCounter + 1, census1);
                    }
                }));
    }

    public void checkMap() throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
    }

    /**
     * Write List Object Into Json Object
     *
     * @param fileName
     */
    public void writeIntoJson(String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

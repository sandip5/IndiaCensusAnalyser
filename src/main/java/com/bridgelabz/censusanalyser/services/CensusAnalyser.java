package com.bridgelabz.censusanalyser.services;

import com.bridgelabz.censusanalyser.adapter.CensusAdapterFactory;
import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.bridgelabz.censusanalyser.adapter.CensusAdapter.censusList;

public class CensusAnalyser {
    Comparator<CensusDAO> censusComparator = null;
    ArrayList censusDTO = null;

    public CensusAnalyser() {
    }

    private Country country;

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    public enum Country {
        INDIA_CENSUS, INDIA_STATE, US_CENSUS
    }

    /**
     * Load Census Data
     *
     * @param csvFilePath
     * @return
     * @throws CensusAnalyserException
     */
    public List loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        return new CensusAdapterFactory().getCensusData(country, csvFilePath);
    }

    /**
     * Get Sorted Data
     *
     * @param sortBy
     * @param fileName
     * @return
     * @throws CensusAnalyserException
     */
    public String getSortedCensusData(String sortBy, String fileName) throws CensusAnalyserException {
        if (censusList == null || censusList.size() == 0)
            throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_DATA);
        switch (sortBy) {
            case "Population":
                censusComparator = Comparator.comparing(census -> census.population);
                censusDTO = this.ascendingSort();
                break;
            case "StateName":
                censusComparator = Comparator.comparing(census -> census.stateName);
                censusDTO = this.ascendingSort();
                break;
            case "StateCode":
                censusComparator = Comparator.comparing(census -> census.stateCode);
                censusDTO = this.ascendingSort();
                break;
            case "PopulationDensity":
                censusComparator = Comparator.comparing(census -> census.populationDensity);
                censusDTO = this.descendingSort();
                break;
            case "TotalArea":
                censusComparator = Comparator.comparing(census -> census.totalArea);
                censusDTO = this.descendingSort();
                break;
            case "HousingUnits":
                censusComparator = Comparator.comparing(census -> census.housingUnits);
                censusDTO = this.descendingSort();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sortBy);
        }
        String sortedJSON = new Gson().toJson(censusDTO);
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(censusList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sortedJSON;
    }

    private ArrayList descendingSort() {
        censusDTO = censusList.stream()
                .sorted(censusComparator.reversed())
                .map(censusDAO -> censusDAO.getCensusDTOS(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return censusDTO;
    }

    private ArrayList ascendingSort() {
        censusDTO = censusList.stream()
                .sorted(censusComparator)
                .map(censusDAO -> censusDAO.getCensusDTOS(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return censusDTO;
    }
}
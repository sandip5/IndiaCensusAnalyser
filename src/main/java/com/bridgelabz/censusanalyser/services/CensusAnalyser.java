package com.bridgelabz.censusanalyser.services;

import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.model.UsCensusCSV;
import com.bridgelabz.censusanalyser.utility.Utility;
import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;

import static com.bridgelabz.censusanalyser.services.CensusLoader.censusList;

public class CensusAnalyser {
    public enum Country {
        INDIA_CENSUS, INDIA_STATE, US_CENSUS
    }

    CensusLoader loaderObject = new CensusLoader();
    Utility utils = new Utility();

    /**
     * Load Census Data
     * @param country
     * @param csvFilePath
     * @return
     * @throws CensusAnalyserException
     */
    public List loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        switch (country) {
            case INDIA_CENSUS:
                return loaderObject.censusLoader(IndiaCensusCSV.class, csvFilePath);
            case INDIA_STATE:
                return loaderObject.censusLoader(IndiaStateCodeCSV.class, csvFilePath);
            case US_CENSUS:
                return loaderObject.censusLoader(UsCensusCSV.class, csvFilePath);
            default:
                throw new CensusAnalyserException("Invalid Country", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
        }
    }

    /**
     * Get Sorted Data
     * @param sortBy
     * @param fileName
     * @return
     * @throws CensusAnalyserException
     */
    public String getSortedCensusData(String sortBy, String fileName) throws CensusAnalyserException {
        utils.checkMap();
        Comparator<CensusDAO> censusComparator = null;
        switch (sortBy) {
            case "Population":
                censusComparator = Comparator.comparing(census -> census.population);
                utils.ascendingSort(censusComparator);
                break;
            case "StateName":
                censusComparator = Comparator.comparing(census -> census.stateName);
                utils.ascendingSort(censusComparator);
                break;
            case "StateCode":
                censusComparator = Comparator.comparing(census -> census.stateCode);
                utils.ascendingSort(censusComparator);
                break;
            case "PopulationDensity":
                censusComparator = Comparator.comparing(census -> census.populationDensity);
                utils.descendingSort(censusComparator);
                break;
            case "TotalArea":
                censusComparator = Comparator.comparing(census -> census.totalArea);
                utils.descendingSort(censusComparator);
                break;
            case "HousingUnits":
                censusComparator = Comparator.comparing(census -> census.housingUnits);
                utils.descendingSort(censusComparator);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sortBy);
        }
        String sortedPopulationCensusJson = new Gson().toJson(censusList);
        utils.writeIntoJson(fileName);
        return sortedPopulationCensusJson;
    }
}
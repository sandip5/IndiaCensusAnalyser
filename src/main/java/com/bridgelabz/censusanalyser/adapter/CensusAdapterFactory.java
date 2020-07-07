package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.services.CensusAnalyser;

import java.util.List;

public class CensusAdapterFactory {
    public List<CensusDAO> getCensusData(CensusAnalyser.Country country, String... csvFilePath)
            throws CensusAnalyserException {
        switch (country) {
            case INDIA_CENSUS:
                return new IndiaCensusAdapter().loadCensusData(csvFilePath);
            case INDIA_STATE:
                return new IndiaStateCodeAdapter().loadCensusData(csvFilePath);
            case US_CENSUS:
                return new USCensusAdapter().loadCensusData(csvFilePath);
            default:
                throw new CensusAnalyserException("Invalid Country", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
        }
    }

}

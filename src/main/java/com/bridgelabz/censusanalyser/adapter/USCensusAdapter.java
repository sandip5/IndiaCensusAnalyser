package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.model.UsCensusCSV;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;

import java.util.List;

public class USCensusAdapter extends CensusAdapter {
    public List<CensusDAO> loadCensusData(String[] csvFilePath) throws CensusAnalyserException {
        List<CensusDAO> usCensusList = super.censusLoader(UsCensusCSV.class, csvFilePath[0]);
        return usCensusList;
    }
}

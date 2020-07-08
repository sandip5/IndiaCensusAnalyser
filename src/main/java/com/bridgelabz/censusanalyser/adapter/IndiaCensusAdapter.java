package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;

import java.util.List;

public class IndiaCensusAdapter extends CensusAdapter {
    @Override
    public List loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        List<CensusDAO> indiaCensusList = super.censusLoader(IndiaCensusCSV.class, csvFilePath[0]);
        return indiaCensusList;
    }
}


package com.bridgelabz.censusanalyser.adapter;

import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;

import java.util.List;

public class IndiaStateCodeAdapter extends CensusAdapter {
    public List<CensusDAO> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        List<CensusDAO> indiaStateList = super.censusLoader(IndiaStateCodeCSV.class, csvFilePath[0]);
        return indiaStateList;
    }
}

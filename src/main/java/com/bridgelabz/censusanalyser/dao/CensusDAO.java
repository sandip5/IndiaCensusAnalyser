package com.bridgelabz.censusanalyser.dao;

import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.model.UsCensusCSV;

public class CensusDAO {
    public double waterArea;
    public String stateName;
    public int population;
    public double totalArea;
    public String stateId;
    public int housingUnits;
    public int tin;
    public String stateCode;
    public int srNo;
    public int populationDensity;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        stateName = indiaCensusCSV.state;
        totalArea = indiaCensusCSV.areaInSqKm;
        populationDensity = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV){
        srNo = indiaStateCodeCSV.srNo;
        stateName = indiaStateCodeCSV.stateName;
        stateCode = indiaStateCodeCSV.stateCode;
        tin = indiaStateCodeCSV.tin;
    }

    public CensusDAO(UsCensusCSV usCensusCSV){
        housingUnits = usCensusCSV.housingUnits;
        stateId = usCensusCSV.stateId;
        totalArea = usCensusCSV.totalArea;
        population = usCensusCSV.usPopulation;
        stateName = usCensusCSV.usState;
        waterArea = usCensusCSV.waterArea;
    }
}
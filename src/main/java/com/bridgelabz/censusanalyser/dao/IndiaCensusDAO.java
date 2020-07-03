package com.bridgelabz.censusanalyser.dao;

import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.model.UsCensusCSV;

public class IndiaCensusDAO {
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

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        stateName = indiaCensusCSV.state;
        totalArea = indiaCensusCSV.areaInSqKm;
        populationDensity = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public IndiaCensusDAO(IndiaStateCodeCSV indiaStateCodeCSV){
        srNo = indiaStateCodeCSV.srNo;
        stateName = indiaStateCodeCSV.stateName;
        stateCode = indiaStateCodeCSV.stateCode;
        tin = indiaStateCodeCSV.tin;
    }

    public IndiaCensusDAO(UsCensusCSV usCensusCSV){
        housingUnits = usCensusCSV.housingUnits;
        stateId = usCensusCSV.stateId;
        totalArea = usCensusCSV.totalArea;
        population = usCensusCSV.usPopulation;
        stateName = usCensusCSV.usState;
        waterArea = usCensusCSV.waterArea;
    }
}
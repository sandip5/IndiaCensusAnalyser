package com.bridgelabz.censusanalyser.model;

public class IndiaCensusDAO {
    public String waterArea;
    public String usState;
    public int usPopulation;
    public String totalArea;
    public String stateId;
    public int housingUnits;
    public int tin;
    public String stateName;
    public String stateCode;
    public int srNo;
    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;
    public String state;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
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
        usPopulation = usCensusCSV.usPopulation;
        usState = usCensusCSV.usState;
        waterArea = usCensusCSV.waterArea;
    }
}

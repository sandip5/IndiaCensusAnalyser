package com.bridgelabz.censusanalyser.model;

public class IndiaCensusDAO {
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
}

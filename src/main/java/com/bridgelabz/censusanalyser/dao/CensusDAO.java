package com.bridgelabz.censusanalyser.dao;

import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.model.UsCensusCSV;
import com.bridgelabz.censusanalyser.services.CensusAnalyser;

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

    public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        srNo = indiaStateCodeCSV.srNo;
        stateName = indiaStateCodeCSV.stateName;
        stateCode = indiaStateCodeCSV.stateCode;
        tin = indiaStateCodeCSV.tin;
    }

    public CensusDAO(UsCensusCSV usCensusCSV) {
        housingUnits = usCensusCSV.housingUnits;
        stateId = usCensusCSV.stateId;
        totalArea = usCensusCSV.totalArea;
        population = usCensusCSV.usPopulation;
        stateName = usCensusCSV.usState;
        waterArea = usCensusCSV.waterArea;
    }

    public Object getCensusDTOS(CensusAnalyser.Country country) {
        if (country.equals(CensusAnalyser.Country.US_CENSUS))
            return new UsCensusCSV(housingUnits, stateId, population, stateName, waterArea);
        else if (country.equals(CensusAnalyser.Country.INDIA_CENSUS))
            return new IndiaCensusCSV(stateName, totalArea, populationDensity, population);
        return new IndiaStateCodeCSV(stateCode);
    }

    @Override
    public String toString() {
        return "CensusDAO{" +
                "WaterArea'" + waterArea + '\'' +
                "StateName='" + stateName + '\'' +
                ", Population='" + population + '\'' +
                ", TotalArea='" + totalArea + '\'' +
                ", StateID='" + stateId + '\'' +
                ", HousingUnits'" + housingUnits + '\'' +
                ", TIN='" + tin + '\'' +
                ", SrNo='" + srNo + '\'' +
                ", PopulationDensity='" + populationDensity + '\'' +
                '}';
    }

}
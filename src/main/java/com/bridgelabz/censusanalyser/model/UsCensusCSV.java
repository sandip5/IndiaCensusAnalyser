package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class UsCensusCSV {
    public UsCensusCSV() {

    }

    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String usState;

    @CsvBindByName(column = "Population", required = true)
    public int usPopulation;

    @CsvBindByName(column = "Housing units", required = true)
    public int housingUnits;

    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;

    @CsvBindByName(column = "Water area", required = true)
    public double waterArea;

    public UsCensusCSV(int housingUnits, String stateId, int population, String stateName, double waterArea) {
        this.housingUnits = housingUnits;
        this.stateId = stateId;
        this.usPopulation = population;
        this.usState = stateName;
        this.waterArea = waterArea;
    }

    @Override
    public String toString() {
        return "UsCensusCSV{" +
                "stateId='" + stateId + '\'' +
                ", state='" + usState + '\'' +
                ", population=" + usPopulation +
                ", housingUnits='" + housingUnits + '\'' +
                ", TotalArea='" + totalArea + '\'' +
                ", waterArea='" + waterArea + '\'' +
                '}';
    }
}

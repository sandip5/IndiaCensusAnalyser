package com.bridgelabz.censusanalyser.dto;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {
    public IndiaCensusCSV(){
    }
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public int areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public int densityPerSqKm;

    public IndiaCensusCSV(String stateName, double totalArea, int populationDensity, int population) {
        this.state = stateName;
        this.areaInSqKm = (int)totalArea;
        this.densityPerSqKm = populationDensity;
        this.population = population;
    }

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "State='" + state + '\'' +
                ", Population='" + population + '\'' +
                ", AreaInSqKm='" + areaInSqKm + '\'' +
                ", DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}

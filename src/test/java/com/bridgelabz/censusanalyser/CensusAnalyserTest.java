package com.bridgelabz.censusanalyser;

import com.bridgelabz.censusanalyser.dto.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.dto.IndiaStateCodeCSV;
import com.bridgelabz.censusanalyser.dto.UsCensusCSV;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.services.CensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static com.bridgelabz.censusanalyser.services.CensusAnalyser.Country.*;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String DELIMITER_PROBLEM_CSV_FILE_PATH = "./src/test/resources/DelimiterProblemIndiaCensus.csv";
    private static final String HEADER_PROBLEM_CSV_FILE_PATH = "./src/test/resources/HeaderProblemIndiaCensus.csv";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_INDIAN_STATE_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_TYPE_INDIAN_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.txt";
    private static final String DELIMITER_PROBLEM_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/DelimiterProblemIndiaStateCode.csv";
    private static final String HEADER_PROBLEM_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/HeaderProblemIndiaStateCode.csv";
    private static final String US_STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/US_STATE_CENSUS.csv";


    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            List numOfRecords = censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithRightFile_ButTypeIncorrect_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithRightFile_ButIncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(DELIMITER_PROBLEM_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_HEADER_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithRightFile_ButIncorrectHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(HEADER_PROBLEM_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_HEADER_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_STATE);
            List numOfRecords = censusAnalyser.loadCensusData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_INDIAN_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithRightFile_ButTypeIncorrect_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_STATE);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(WRONG_TYPE_INDIAN_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithRightFile_ButIncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_STATE);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(DELIMITER_PROBLEM_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_HEADER_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithRightFile_ButIncorrectHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_STATE);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(HEADER_PROBLEM_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_HEADER_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/StateWiseSortedIndiaCensus.json";
            String sortBy = "StateName";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            System.out.println(sortedCensusData);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCSV_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_STATE);
            censusAnalyser.loadCensusData(INDIA_STATE_CODE_CSV_FILE_PATH);
            String fileName = "./src/test/resources/StateCodeWiseSortedIndiaCensus.json";
            String sortBy = "StateCode";
            String sortedStateCodeData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            IndiaStateCodeCSV[] stateCodeCSV = new Gson().fromJson(sortedStateCodeData, IndiaStateCodeCSV[].class);
            Assert.assertEquals("AD", stateCodeCSV[0].stateCode);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/PopulationWiseSortedIndiaCensus.json";
            String sortBy = "Population";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341, censusCSV[censusCSV.length - 1].population);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/PopulationDensityWiseSortedIndiaCensus.json";
            String sortBy = "PopulationDensity";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1102, censusCSV[0].densityPerSqKm);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/AreaWiseSortedIndiaCensus.json";
            String sortBy = "TotalArea";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(342239, censusCSV[0].areaInSqKm);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(US_CENSUS);
            censusAnalyser.loadCensusData(US_STATE_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/PopulationWiseSortedIndiaCensus.json";
            String sortBy = "Population";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            UsCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, UsCensusCSV[].class);
            Assert.assertEquals(37253956, censusCSV[censusCSV.length - 1].usPopulation);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnHousingUnitWise_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(US_CENSUS);
            censusAnalyser.loadCensusData(US_STATE_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/HousingUnitWiseSortedIndiaCensus.json";
            String sortBy = "HousingUnits";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            UsCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, UsCensusCSV[].class);
            Assert.assertEquals(13680081, censusCSV[0].housingUnits);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUSCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(US_CENSUS);
            List numOfRecords = censusAnalyser.loadCensusData(US_STATE_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(45, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUSCensusData_WhenSortingPopulationWise_ShouldReturnMostPopulousState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(US_CENSUS);
            censusAnalyser.loadCensusData(US_STATE_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/MostPopulousStateWiseSortedIndiaCensus.json";
            String sortBy = "Population";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            UsCensusCSV[] usCensusCSV = new Gson().fromJson(sortedCensusData, UsCensusCSV[].class);
            Assert.assertEquals("California", usCensusCSV[usCensusCSV.length - 1].usState);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WhenSortingPopulationWise_ShouldReturnMostPopulousState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            censusAnalyser.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/PopulationWiseSortedIndiaCensus.json";
            String sortBy = "Population";
            String sortedIndiaCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortedIndiaCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", indiaCensusCSV[indiaCensusCSV.length - 1].state);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }
}

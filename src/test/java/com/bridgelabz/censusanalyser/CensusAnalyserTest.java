package com.bridgelabz.censusanalyser;

import com.bridgelabz.censusanalyser.dao.CensusDAO;
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
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            List numOfRecords = censusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_CENSUS, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithRightFile_ButTypeIncorrect_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_CENSUS, WRONG_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithRightFile_ButIncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_CENSUS, DELIMITER_PROBLEM_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_HEADER_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WithRightFile_ButIncorrectHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_CENSUS, HEADER_PROBLEM_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_HEADER_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            List numOfRecords = censusAnalyser.loadCensusData(INDIA_STATE, INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_CENSUS, WRONG_INDIAN_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithRightFile_ButTypeIncorrect_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_STATE, WRONG_TYPE_INDIAN_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithRightFile_ButIncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_STATE, DELIMITER_PROBLEM_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_HEADER_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCensusData_WithRightFile_ButIncorrectHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(INDIA_STATE, HEADER_PROBLEM_STATE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DELIMITER_HEADER_PROBLEM, e.type);
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/StateWiseSortedIndiaCensus.json";
            String sortBy = "StateName";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].stateName);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaStateCodeCSV_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_STATE_CODE_CSV_FILE_PATH);
            String fileName = "./src/test/resources/StateCodeWiseSortedIndiaCensus.json";
            String sortBy = "StateCode";
            String sortedStateCodeData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            CensusDAO[] stateCodeCSV = new Gson().fromJson(sortedStateCodeData, CensusDAO[].class);
            Assert.assertEquals("AD", stateCodeCSV[0].stateCode);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/PopulationWiseSortedIndiaCensus.json";
            String sortBy = "Population";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(199812341, censusCSV[censusCSV.length - 1].population);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/PopulationDensityWiseSortedIndiaCensus.json";
            String sortBy = "PopulationDensity";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(1102, censusCSV[0].populationDensity);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/AreaWiseSortedIndiaCensus.json";
            String sortBy = "TotalArea";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(342239, (int) censusCSV[0].totalArea);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(US_CENSUS, US_STATE_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/PopulationWiseSortedIndiaCensus.json";
            String sortBy = "Population";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(37253956, (int) censusCSV[censusCSV.length - 1].population);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnHousingUnitWise_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(US_CENSUS, US_STATE_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/HousingUnitWiseSortedIndiaCensus.json";
            String sortBy = "HousingUnits";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            System.out.println(censusCSV[0].stateName);
            Assert.assertEquals(13680081, censusCSV[0].housingUnits);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUSCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            List numOfRecords = censusAnalyser.loadCensusData(US_CENSUS, US_STATE_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(45, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUSCensusData_WhenSortingPopulationWise_ShouldReturnMostPopulousState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(US_CENSUS, US_STATE_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/MostPopulousStateWiseSortedIndiaCensus.json";
            String sortBy = "Population";
            String sortedCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            CensusDAO[] usCensusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("California", usCensusCSV[usCensusCSV.length - 1].stateName);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenIndiaCensusData_WhenSortingPopulationWise_ShouldReturnMostPopulousState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(INDIA_CENSUS, INDIA_CENSUS_CSV_FILE_PATH);
            String fileName = "./src/test/resources/PopulationWiseSortedIndiaCensus.json";
            String sortBy = "Population";
            String sortedIndiaCensusData = censusAnalyser.getSortedCensusData(sortBy, fileName);
            CensusDAO[] indiaCensusCSV = new Gson().fromJson(sortedIndiaCensusData, CensusDAO[].class);
            Assert.assertEquals("Uttar Pradesh", indiaCensusCSV[indiaCensusCSV.length - 1].stateName);
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }
}

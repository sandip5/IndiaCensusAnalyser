package com.bridgelabz.censusanalyser.test;

import com.bridgelabz.censusanalyser.adapter.CensusAdapter;
import com.bridgelabz.censusanalyser.dao.CensusDAO;
import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.services.CensusAnalyser;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;
import java.util.List;

import static com.bridgelabz.censusanalyser.CensusAnalyserTest.INDIA_CENSUS_CSV_FILE_PATH;
import static com.bridgelabz.censusanalyser.CensusAnalyserTest.INDIA_STATE_CODE_CSV_FILE_PATH;
import static com.bridgelabz.censusanalyser.adapter.CensusAdapter.censusList;
import static com.bridgelabz.censusanalyser.services.CensusAnalyser.Country.INDIA_CENSUS;
import static com.bridgelabz.censusanalyser.services.CensusAnalyser.Country.INDIA_STATE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CensusAnalyserMockito {
    @Mock
    CensusDAO  censusDAO;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_CENSUS);
            CensusAdapter censusAdapter = mock(CensusAdapter.class);
            when(censusAdapter.censusLoader(IndiaCensusCSV.class,
                    INDIA_CENSUS_CSV_FILE_PATH )).thenReturn(Collections.singletonList(29));
            List numOfRecords = censusAnalyser.loadCensusData("");
            Assert.assertEquals(29, censusList.size());
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void givenIndiaStateCodeCSVFile_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(INDIA_STATE);
            CensusAdapter censusAdapter = mock(CensusAdapter.class);
            when(censusAdapter.censusLoader(IndiaCensusCSV.class,
                    INDIA_STATE_CODE_CSV_FILE_PATH )).thenReturn(Collections.singletonList(37));
            List numOfRecords = censusAnalyser.loadCensusData("");
            Assert.assertEquals(37, numOfRecords.size());
        } catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
        }
    }
}

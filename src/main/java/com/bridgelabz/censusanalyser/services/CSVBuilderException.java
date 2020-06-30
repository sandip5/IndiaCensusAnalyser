package com.bridgelabz.censusanalyser.services;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;

public class CSVBuilderException extends Throwable {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM,
        DELIMITER_HEADER_PROBLEM
    }

    CensusAnalyserException.ExceptionType type;

    public CSVBuilderException(String message, CensusAnalyserException.ExceptionType type) {
        super(message);
        this.type = type;
    }
}

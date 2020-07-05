package com.bridgelabz.censusanalyser.exception;

public class CensusAnalyserException extends Exception {

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public enum ExceptionType {
        CENSUS_FILE_PROBLEM,
        DELIMITER_HEADER_PROBLEM,
        NO_CENSUS_DATA,
       INVALID_COUNTRY;
    }

    public ExceptionType type;

    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

}

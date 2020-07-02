package com.bridgelabz.censusanalyser.exception;

public class CSVBuilderException extends Throwable {
    public enum ExceptionType {
        CENSUS_FILE_PROBLEM,
        DELIMITER_HEADER_PROBLEM
    }
    public ExceptionType type;
    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}

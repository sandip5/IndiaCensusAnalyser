package com.bridgelabz.censusanalyser.utility;

import com.bridgelabz.censusanalyser.exception.CSVBuilderException;

import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;

    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException;

    public <E> HashMap<E, E> getCSVFileMap(Reader reader, Class csvClass) throws CSVBuilderException;
}

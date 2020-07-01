package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

        @CsvBindByName(column = "SrNo", required = true)
        public int srNo;

        @CsvBindByName(column = "State Name", required = true)
        public String state;

        @CsvBindByName(column = "TIN", required = true)
        public int tin;

        @CsvBindByName(column = "StateCode", required = true)
        public String stateCode;

        @Override
        public String toString() {
            return "IndiaStateCodeCSV{" +
                    "SrNo='" + srNo + '\'' +
                    "State Name='" + state + '\'' +
                    ", TIN='" + tin + '\'' +
                    ", StateCode='" + stateCode + '\'' +
                    '}';
        }

}

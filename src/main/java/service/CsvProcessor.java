package com.example.reportgenerator.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvProcessor {

    public List<InputRecord> readInputFile(File inputFile) throws IOException {
        List<InputRecord> inputRecords = new ArrayList<>();
        try (Reader reader = new FileReader(inputFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                InputRecord record = new InputRecord(
                        csvRecord.get("field1"),
                        csvRecord.get("field2"),
                        csvRecord.get("field3"),
                        csvRecord.get("field4"),
                        csvRecord.get("field5"),
                        csvRecord.get("refkey1"),
                        csvRecord.get("refkey2")
                );
                inputRecords.add(record);
            }
        }
        return inputRecords;
    }

    public List<ReferenceRecord> readReferenceFile(File referenceFile) throws IOException {
        List<ReferenceRecord> referenceRecords = new ArrayList<>();
        try (Reader reader = new FileReader(referenceFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                ReferenceRecord record = new ReferenceRecord(
                        csvRecord.get("refkey1"),
                        csvRecord.get("refdata1"),
                        csvRecord.get("refkey2"),
                        csvRecord.get("refdata2"),
                        csvRecord.get("refdata3"),
                        csvRecord.get("refdata4")
                );
                referenceRecords.add(record);
            }
        }
        return referenceRecords;
    }

    public void writeOutputFile(File outputFile, List<OutputRecord> outputRecords) throws IOException {
        try (Writer writer = new FileWriter(outputFile);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("outfield1", "outfield2", "outfield3", "outfield4", "outfield5"))) {
            for (OutputRecord record : outputRecords) {
                csvPrinter.printRecord(
                        record.getOutfield1(),
                        record.getOutfield2(),
                        record.getOutfield3(),
                        record.getOutfield4(),
                        record.getOutfield5()
                );
            }
        }
    }
}

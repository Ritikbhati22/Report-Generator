package com.example.reportgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ReportGenerationService {

    @Autowired
    private FileIngestionService fileIngestionService;

    @Autowired
    private DataTransformationService dataTransformationService;

    public void generateReport(String inputFilePath, String referenceFilePath, String outputFilePath) throws IOException {
        List<String[]> inputData = fileIngestionService.readCsvFile(inputFilePath);
        List<String[]> referenceData = fileIngestionService.readCsvFile(referenceFilePath);

        try (FileWriter writer = new FileWriter(outputFilePath)) {
            for (String[] inputRow : inputData) {
                String[] refData = findReferenceData(inputRow[6], referenceData); // Simplified for demo
                String[] transformedRow = dataTransformationService.transformData(inputRow, refData);
                writer.write(String.join(",", transformedRow) + "\n");
            }
        }
    }

    private String[] findReferenceData(String refKey, List<String[]> referenceData) {
        for (String[] row : referenceData) {
            if (row[0].equals(refKey)) {
                return row;
            }
        }
        return new String[]{};
    }
}

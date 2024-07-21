package com.example.reportgenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private CsvProcessor csvProcessor;

    @Autowired
    private TransformationService transformationService;

    public void generateReports() {
        // Logic to locate input and reference files
        // Read input and reference files
        // Process files using csvProcessor and transformationService
        // Generate output files
    }
}

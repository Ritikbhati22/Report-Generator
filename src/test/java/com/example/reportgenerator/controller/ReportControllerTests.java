package com.example.reportgenerator.controller;

import com.example.reportgenerator.service.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportGenerationService reportGenerationService;

    @PostMapping("/generate")
    public String generateReport(@RequestParam String inputFilePath, @RequestParam String referenceFilePath, @RequestParam String outputFilePath) {
        try {
            reportGenerationService.generateReport(inputFilePath, referenceFilePath, outputFilePath);
            return "Report generated successfully";
        } catch (IOException e) {
            return "Error generating report: " + e.getMessage();
        }
    }
}

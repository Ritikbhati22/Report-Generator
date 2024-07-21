package com.example.reportgenerator.config;

import com.example.reportgenerator.service.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class SchedulingConfig {

    @Autowired
    private ReportGenerationService reportGenerationService;

    @Scheduled(cron = "0 0 * * * ?") // Runs every hour, adjust as needed
    public void scheduledReportGeneration() {
        try {
            reportGenerationService.generateReport("input.csv", "reference.csv", "output.csv");
        } catch (IOException e) {
            e.printStackTrace(); // Add proper logging here
        }
    }
}

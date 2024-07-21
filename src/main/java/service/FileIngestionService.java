package com.example.reportgenerator.service;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileIngestionService {

    public List<String[]> readCsvFile(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                data.add(nextLine);
            }
        }
        return data;
    }
}

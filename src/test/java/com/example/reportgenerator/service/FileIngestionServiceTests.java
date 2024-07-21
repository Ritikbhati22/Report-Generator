package com.example.reportgenerator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FileIngestionServiceTests {

    @Autowired
    private FileIngestionService fileIngestionService;

    @Test
    void testReadCsvFile() throws IOException {
        List<String[]> data = fileIngestionService.readCsvFile("src/test/resources/input.csv");
        assertEquals(1, data.size());
        assertEquals("field1", data.get(0)[0]);
    }
}

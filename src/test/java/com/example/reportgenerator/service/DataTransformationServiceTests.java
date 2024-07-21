package com.example.reportgenerator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DataTransformationServiceTests {

    @Autowired
    private DataTransformationService dataTransformationService;

    @Test
    void testTransformData() {
        String[] inputRow = {"field1", "field2", "field3", "field4", "100"};
        String[] referenceData = {"key1", "refdata1", "refdata2", "refdata3", "200"};

        String[] result = dataTransformationService.transformData(inputRow, referenceData);
        assertEquals("field1field2", result[0]);
        assertEquals("refdata1", result[1]);
        assertEquals("refdata2refdata3", result[2]);
        assertEquals("300.0", result[3]);
        assertEquals("200.0", result[4]);
    }
}

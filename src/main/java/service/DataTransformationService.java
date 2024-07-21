package com.example.reportgenerator.service;

import org.springframework.stereotype.Service;

@Service
public class DataTransformationService {

    public String[] transformData(String[] inputRow, String[] referenceData) {
        String outfield1 = inputRow[0] + inputRow[1];
        String outfield2 = referenceData[1];
        String outfield3 = referenceData[2] + referenceData[3];
        double field5 = Double.parseDouble(inputRow[4]);
        double refData4 = Double.parseDouble(referenceData[5]);
        double maxField = Math.max(field5, refData4);
        String outfield4 = String.valueOf(Double.parseDouble(inputRow[2]) * maxField);
        String outfield5 = String.valueOf(maxField);
        return new String[]{outfield1, outfield2, outfield3, outfield4, outfield5};
    }
}

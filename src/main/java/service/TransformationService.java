package com.example.reportgenerator.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransformationService {

    public List<OutputRecord> transform(List<InputRecord> inputRecords, List<ReferenceRecord> referenceRecords) {
        List<OutputRecord> outputRecords = new ArrayList<>();
        for (InputRecord inputRecord : inputRecords) {
            Optional<ReferenceRecord> optionalReferenceRecord = referenceRecords.stream()
                    .filter(ref -> ref.getRefkey1().equals(inputRecord.getRefkey1()) && ref.getRefkey2().equals(inputRecord.getRefkey2()))
                    .findFirst();
            if (optionalReferenceRecord.isPresent()) {
                ReferenceRecord referenceRecord = optionalReferenceRecord.get();
                OutputRecord outputRecord = new OutputRecord(
                        inputRecord.getField1() + inputRecord.getField2(),
                        referenceRecord.getRefdata1(),
                        referenceRecord.getRefdata2() + referenceRecord.getRefdata3(),
                        new BigDecimal(inputRecord.getField3()).multiply(new BigDecimal(Math.max(
                                Double.parseDouble(inputRecord.getField5()), Double.parseDouble(referenceRecord.getRefdata4())))).toString(),
                        String.valueOf(Math.max(
                                Double.parseDouble(inputRecord.getField5()), Double.parseDouble(referenceRecord.getRefdata4())))
                );
                outputRecords.add(outputRecord);
            }
        }
        return outputRecords;
    }
}

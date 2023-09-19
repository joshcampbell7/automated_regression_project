package com.automation.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@Document("TestResult")
public class TestResult {
    @MongoId
    @Field
    private String testResultId;
    @Field
    private String testId;
    @Field
    private String date;
    @Field
    private Boolean result;

    public TestResult(String testId,String date,Boolean result) {
        setTestId(testId);
        setDate(date);
        setResult(result);
    }
}

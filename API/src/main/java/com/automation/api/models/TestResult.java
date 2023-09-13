package com.automation.api.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
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
    private String result;

}

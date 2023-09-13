package com.automation.api.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document("TestStepResult")
public class TestStepResult {
    @MongoId
    @Field
    private String testStepResultId;
    @Field
    private String testStepId;
    @Field
    private String testResultId;
    @Field
    private String reasonForFail;
    @Field
    private String result;

}

package com.automation.api.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document("TestStep")
public class TestStep {
    @MongoId
    @Field
    private String testStepId;
    @Field
    private int testStepNumber;
    @Field
    private Action action;
    @Field
    private String testStepDescription;
    @Field
    private String value;
    @Field
    private Element element;

}

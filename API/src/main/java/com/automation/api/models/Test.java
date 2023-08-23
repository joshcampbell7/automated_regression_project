package com.automation.api.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document("Test")
public class Test {
    @MongoId
    @Field
    private String testId;
    @Field
    private String testName;
    @Field
    private String testDescription;
    @Field
    private String dateCreated;
    @Field
    private String userId;

}

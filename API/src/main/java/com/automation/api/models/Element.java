package com.automation.api.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document("Element")
public class Element {
    @MongoId
    @Field
    private String elementId;
    @Field
    private String elementName;
    @Field
    private String elementValue;
    @Field
    private String elementType;
    @Field
    private String userId;

}

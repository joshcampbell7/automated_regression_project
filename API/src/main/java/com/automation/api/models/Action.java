package com.automation.api.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document("Action")
public class Action {
    @MongoId
    @Field
    private String actionId;
    @Field
    private String actionName;
    @Field
    private String actionDescription;
    @Field
    private String actionInput;
    @Field
    private ActionType actionType;


}

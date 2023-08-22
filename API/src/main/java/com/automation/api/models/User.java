package com.automation.api.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Document("User")
public class User {
    @MongoId
    @Field
    private String userId;
    @Field
    private String email;
    @Field
    private String firstName;
    @Field
    private String lastName;
    @Field
    private String password;

}

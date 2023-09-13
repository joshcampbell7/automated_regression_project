package com.automation.api.repos;

import com.automation.api.models.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestRepo extends MongoRepository<Test,String> {
    List<Test> getTestByUserId(String userId);
    Test findTestByTestId(String testId);
}

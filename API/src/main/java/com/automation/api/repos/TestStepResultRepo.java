package com.automation.api.repos;

import com.automation.api.models.TestStepResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestStepResultRepo extends MongoRepository<TestStepResult,String> {
}

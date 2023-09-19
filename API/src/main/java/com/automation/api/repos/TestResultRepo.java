package com.automation.api.repos;

import com.automation.api.models.TestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestResultRepo extends MongoRepository<TestResult,String> {
}

package com.automation.api.repos;

import com.automation.api.models.TestStep;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestStepRepo extends MongoRepository<TestStep,String> {

    List<TestStep> findByTestIdOrderByTestStepNumberAsc(String testId);
}

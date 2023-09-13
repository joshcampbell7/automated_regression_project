package com.automation.api.services;

import com.automation.api.models.Test;
import com.automation.api.models.TestStep;
import com.automation.api.repos.TestRepo;
import com.automation.api.repos.TestStepRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestStepService {
    @Autowired
    TestStepRepo testStepRepo;

    public List<TestStep> findTestStepsBytestId(String testId){
        return testStepRepo.findByTestIdOrderByTestStepNumberAsc(testId);
    }

}

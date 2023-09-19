package com.automation.api.services;

import com.automation.api.models.TestStepResult;
import com.automation.api.repos.TestStepResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestStepResultService {
    @Autowired
    TestStepResultRepo testStepResultRepo;
    public void saveTestStepResult(TestStepResult testStepResult){
        testStepResultRepo.save(testStepResult);
    }

}

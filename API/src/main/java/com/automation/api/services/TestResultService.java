package com.automation.api.services;

import com.automation.api.models.TestResult;
import com.automation.api.repos.TestResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestResultService {
    @Autowired
    TestResultRepo testResultRepo;

    public TestResult saveTestResult(TestResult testResult){
        return testResultRepo.save(testResult);
    }
}

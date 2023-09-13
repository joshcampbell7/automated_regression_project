package com.automation.api.services;

import com.automation.api.models.Test;
import com.automation.api.repos.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    TestRepo testRepo;

    public List<Test> getTestsByUserId(String userId){
        return testRepo.getTestByUserId(userId);
    }

    public Test addNewTest(Test test){
        return testRepo.save(test);
    }

    public Test findTestByTestId(String testId){
        return testRepo.findTestByTestId(testId);
    }
}

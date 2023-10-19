package com.automation.api.services;

import com.automation.api.models.TestStep;
import com.automation.api.repos.TestStepRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestStepService {
    @Autowired
    TestStepRepo testStepRepo;

    public List<TestStep> findTestStepsBytestId(String testId) {
        return testStepRepo.findByTestIdOrderByTestStepNumberAsc(testId);
    }

    public TestStep updateOrAddTestStep(TestStep testStep) {
        return testStepRepo.save(testStep);
    }

    public void deleteTestStep(TestStep testStep) {
        testStepRepo.deleteTestStepByTestStepId(testStep.getTestStepId());

    }

    public void synchronizeTestSteps(List<TestStep> originalTestSteps, List<TestStep> updatedTestSteps) {
        List<TestStep> testStepsToDelete = new ArrayList<>();

        for (TestStep originalTestStep : originalTestSteps) {
            boolean found = false;
            for (TestStep updatedTestStep : updatedTestSteps) {
                if (originalTestStep.getTestStepId().equals(updatedTestStep.getTestStepId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                testStepsToDelete.add(originalTestStep);
            }
        }

        for (TestStep testStepToDelete : testStepsToDelete) {
            deleteTestStep(testStepToDelete);
        }

        int i = 1;
        for (TestStep updatedTestStep : updatedTestSteps) {
            if (!originalTestSteps.contains(updatedTestStep)) {
                updatedTestStep.setTestStepNumber(i);
                updateOrAddTestStep(updatedTestStep);
                i++;
            }
        }
    }

}

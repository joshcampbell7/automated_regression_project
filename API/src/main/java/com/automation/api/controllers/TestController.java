package com.automation.api.controllers;

import com.automation.api.automation_tool.KeywordDriver;
import com.automation.api.models.*;
import com.automation.api.services.TestResultService;
import com.automation.api.services.TestService;
import com.automation.api.services.TestStepResultService;
import com.automation.api.services.TestStepService;
import com.automation.api.utils.DriverUtils;
import com.automation.api.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    TestStepService testStepService;
    @Autowired
    Utils utils;
    @Autowired
    DriverUtils driverUtils;
    @Autowired
    TestResultService testResultService;
    @Autowired
    TestStepResultService testStepResultService;


    public KeywordDriver keywordDriver;

    @GetMapping("/api/user/tests/{userId}")
    public ResponseEntity<List<Test>> getAllTestsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(testService.getTestsByUserId(userId));
    }

    @PostMapping(value = "/api/add/test", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> addTest(@RequestBody Test newTest) {
        Map<String, Object> response = new HashMap<>();
        Test test = testService.addNewTest(newTest);
        response.put("testId", test.getTestId());
        response.put("testName", test.getTestName());
        response.put("testDescription", test.getTestDescription());
        response.put("dateCreated", test.getDateCreated());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/tests/{testId}")
    public ResponseEntity<Map<String, Object>> getTestByTestId(@PathVariable String testId) {
        Map<String, Object> response = new HashMap<>();
        Test test = testService.findTestByTestId(testId);
        if (test == null) {
            response.put("error", "not a valid testId");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.put("testId", testId);
        response.put("testName", test.getTestName());
        response.put("testDescription", test.getTestDescription());
        response.put("dateCreated", test.getDateCreated());
        response.put("testSteps", testStepService.findTestStepsBytestId(testId));
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/api/update/test")
    public ResponseEntity<Map<String, Object>> updateTest(@RequestBody Test updatedTest) {
        Map<String, Object> response = new HashMap<>();

        Test test = testService.findTestByTestId(updatedTest.getTestId());
        if (test == null) {
            response.put("error", "not a valid testId");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        List<TestStep> originalTestSteps = testStepService.findTestStepsBytestId(test.getTestId());
        List<TestStep> updatedTestSteps = updatedTest.getTestSteps();
        testStepService.synchronizeTestSteps(originalTestSteps, updatedTestSteps);

        response.put("testId", test.getTestId());
        response.put("testName", test.getTestName());
        response.put("testSteps", testStepService.findTestStepsBytestId(test.getTestId()));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/api/test/run/{testId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> addTest(@PathVariable String testId) {
        Map<String, Object> response = new HashMap<>();
        Test test = testService.findTestByTestId(testId);
        if (test == null) {
            response.put("error", "not a valid test");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        // create chrome driver and start selenium recording
        WebDriver driver = driverUtils.initDriver();
        if (driver == null) {
            response.put("error", "webdriver is null");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        // code needed to getTestSteps via testId in a list. Go through the list and complete the action.

        List<TestStep> testSteps = testStepService.findTestStepsBytestId(testId);
        if (testSteps == null) {
            response.put("error", "cant run test as there are no test steps");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        TestResult testResult = new TestResult(testId, null, true);
        Boolean testFailed = false;

        //loops through each step and does step.
//        for (TestStep step : testSteps) {
//            TestStepResult result = keywordDriver.performAction(step, driver);
//            if (result.getResult() == false && testFailed == false) {
//                testResult.setResult(false);
//            }
//            result.setTestStepId(step.getTestStepId());
//            result.setTestResultId(testResult.getTestResultId());
//            testStepResultService.saveTestStepResult(result);
//        }
        testResult.setDate(utils.getTodayDate());
        testResult = testResultService.saveTestResult(testResult);

        // code needed to get the id of the saved result and then return it in the response so that the user can view test results
        //code needed to stop recording and save it somewhere?
        driver.close();
        response.put("TestResultId", testResult.getTestResultId());
        return ResponseEntity.ok().body(response);
    }

}

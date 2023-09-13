package com.automation.api.controllers;

import com.automation.api.automation_tool.KeywordDriver;
import com.automation.api.models.Test;
import com.automation.api.models.TestStep;
import com.automation.api.models.User;
import com.automation.api.repos.TestStepRepo;
import com.automation.api.services.TestService;
import com.automation.api.services.TestStepService;
import com.automation.api.utils.DriverUtils;
import com.automation.api.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public KeywordDriver keywordDriver;

    @GetMapping("/api/tests/{userId}")
    public ResponseEntity<List<Test>> getAllTestsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(testService.getTestsByUserId(userId));
    }

    @PostMapping(value = "/api/add/test", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Test> addTest(@RequestBody Test newTest) {
        Test test = testService.addNewTest(newTest);
        return ResponseEntity.ok().body(test);
    }

    @PostMapping(value = "/api/test/run/{testId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String,Object>> addTest(@PathVariable String testId) {
        Map<String,Object> response = new HashMap<>();
        Test test = testService.findTestByTestId(testId);
        if(test == null){
            response.put("error","not a valid test");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        // create chrome driver and start selenium recording
        WebDriver driver = driverUtils.initDriver();
        if(driver==null){
            response.put("error","webdriver is null");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        // code needed to getTestSteps via testId in a list. Go through the list and complete the action.

        List<TestStep> testSteps = testStepService.findTestStepsBytestId(testId);
        if(testSteps == null){
            response.put("error","cant run test as there are no test steps");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        for(TestStep step : testSteps){
            // call the Keyword driver class
            keywordDriver.performAction(step,driver);
        }

        // code needed to get the id of the saved result and then return it in the response so that the user can view test results
        //code needed to stop recording and save it somewhere?
        driver.close();
        return ResponseEntity.ok().body(response);
    }

}

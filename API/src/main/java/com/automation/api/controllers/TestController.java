package com.automation.api.controllers;

import com.automation.api.models.Test;
import com.automation.api.services.TestService;
import com.automation.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    Utils utils;

    @GetMapping("/api/tests/{userId}")
    public ResponseEntity<List<Test>> getAllTestsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(testService.getTestsByUserId(userId));
    }

    @PostMapping(value = "/api/add/test", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Test> addTest(@RequestBody Test newTest) {
        Test test = testService.addNewTest(newTest);
        return ResponseEntity.ok().body(test);
    }

}

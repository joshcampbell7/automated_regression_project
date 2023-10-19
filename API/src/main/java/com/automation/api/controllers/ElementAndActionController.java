package com.automation.api.controllers;

import com.automation.api.models.Action;
import com.automation.api.models.Element;
import com.automation.api.models.Test;
import com.automation.api.models.User;
import com.automation.api.services.ElementAndActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ElementAndActionController {

    @Autowired
    ElementAndActionService elementAndActionService;

    @GetMapping("/api/actions")
    public ResponseEntity<List<Action>> getAllActions() {
        return ResponseEntity.ok().body(elementAndActionService.findAllActions());
    }

    @GetMapping("/api/elements/{userId}")
    public ResponseEntity<List<Element>> getElementsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(elementAndActionService.findElementByUserId(userId));
    }

    @PostMapping(value = "/api/add/element", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Element> addNewElement(@RequestBody Element element) {
        if (elementAndActionService.findElementByName(element.getElementName()) != null) {
            return (ResponseEntity<Element>) ResponseEntity.status(HttpStatus.FOUND);
        }

        elementAndActionService.addElement(element);
        return ResponseEntity.ok().body(element);
    }

    @PostMapping(value = "/api/add/action", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Action> addNewAction(@RequestBody Action action) {
        if (elementAndActionService.findActionByActionId(action.getActionName()) != null) {
            return (ResponseEntity<Action>) ResponseEntity.status(HttpStatus.FOUND);
        }

        elementAndActionService.addAction(action);
        return ResponseEntity.ok().body(action);
    }

}

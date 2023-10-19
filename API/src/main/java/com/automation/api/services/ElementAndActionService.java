package com.automation.api.services;

import com.automation.api.models.Action;
import com.automation.api.models.Element;
import com.automation.api.repos.ActionRepo;
import com.automation.api.repos.ElementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementAndActionService {
    @Autowired
    ActionRepo actionRepo;
    @Autowired
    ElementRepo elementRepo;

    public Action addAction(Action action){
        return actionRepo.save(action);
    }

    public Element addElement(Element element){
        return elementRepo.save(element);
    }

    public Element findElementByElementId(String elementId){
        return elementRepo.getElementByElementId(elementId);
    }

    public List<Element> findElementByUserId(String userId){
        return elementRepo.findAllByUserId(userId);
    }

    public Element findElementByName(String elementName){
        return elementRepo.findElementByElementName(elementName);
    }


    public Action findActionByActionId(String actionId){
        return actionRepo.getActionByActionId(actionId);
    }

    public List<Action> findAllActions(){
        return actionRepo.findAll();
    }

    public List<Element> findAllElements(){
        return elementRepo.findAll();
    }
}

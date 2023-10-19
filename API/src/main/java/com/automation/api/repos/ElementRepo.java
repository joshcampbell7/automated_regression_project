package com.automation.api.repos;

import com.automation.api.models.Element;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ElementRepo extends MongoRepository<Element, String> {
    Element getElementByElementId(String elementId);
    List<Element> findAllByUserId(String userId);
    Element findElementByElementName(String elementName);
}

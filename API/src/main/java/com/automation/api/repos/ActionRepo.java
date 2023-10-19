package com.automation.api.repos;

import com.automation.api.models.Action;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActionRepo extends MongoRepository<Action, String> {

    Action getActionByActionId(String actionId);
}

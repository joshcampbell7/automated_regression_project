package com.automation.api.automation_tool;

import com.automation.api.models.Action;
import com.automation.api.models.TestStep;
import org.openqa.selenium.WebDriver;

public class KeywordDriver {

    public void performAction(TestStep step, WebDriver driver){
        // switch statement of different actionType.
        switch (step.getAction().getActionType()){
            case UTIL_ACTION:
                break;

            case ACTION_ON_API:
                break;

            case ACTION_ON_ELEMENT:
                break;
        }

    }


}

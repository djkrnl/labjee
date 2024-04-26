package com.example.labjee.helpers.forcedImplementation;

import com.example.labjee.helpers.forcedImplementation.Programmer;
import com.example.labjee.helpers.forcedImplementation.ProjectManager;

public static class ExpressUse {
    public String useExpress() {
        Express express = new Express();
        express.pushToList(new Programmer("Jesse"));
        express.pushToList(new ProjectManager("Walt"));
        express.pushToList(new Programmer("Mike"));
        express.pushToList(new ProjectManager("Gus"));
        return express.getTotalData();
    }
}
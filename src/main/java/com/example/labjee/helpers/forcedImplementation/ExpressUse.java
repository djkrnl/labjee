package com.example.labjee.helpers.forcedImplementation;

public class ExpressUse {
    public String useExpress() {
        Express express = new Express();
        express.pushToList(new Programmer("Jesse"));
        express.pushToList(new ProjectManager("Walt"));
        express.pushToList(new Programmer("Mike"));
        express.pushToList(new ProjectManager("Gus"));
        return express.serveQueue();
    }
}
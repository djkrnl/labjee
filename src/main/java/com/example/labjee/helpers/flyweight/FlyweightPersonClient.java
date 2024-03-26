package com.example.labjee.helpers.flyweight;

public class FlyweightPersonClient {
    private FlyweightPerson person;

    public FlyweightPersonClient(String type) {
        person = FlyweightPersonFactory.getPerson(type);
    }

    public String getText(String name) {
        return name + person.getRoleText();
    }
}

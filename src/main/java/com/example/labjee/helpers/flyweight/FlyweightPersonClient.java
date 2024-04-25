package com.example.labjee.helpers.flyweight;

public class FlyweightPersonClient {
    //Tydzień 8 - odwrócenie zależności - wykorzystanie obiektu klasy abstrakcyjnej
    private FlyweightPerson person;

    public FlyweightPersonClient(String type) {
        person = FlyweightPersonFactory.getPerson(type);
    }

    public String getText(String name) {
        return name + person.getRoleText();
    }
}

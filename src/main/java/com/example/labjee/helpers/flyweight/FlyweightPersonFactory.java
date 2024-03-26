package com.example.labjee.helpers.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightPersonFactory {
    private static Map<String, FlyweightPerson> flyweights = new HashMap<>();

    public static FlyweightPerson getPerson(String key) {
        if (flyweights.containsKey(key)) {
            return flyweights.get(key);
        }
        FlyweightPerson person;
        switch (key.toLowerCase()) {
            case "actor":
                person = new FlyweightActor();
                person.role = "actor";
                break;
            case "staff":
                person = new FlyweightStaff();
                person.role = "staff";
                break;
            default:
                throw new IllegalArgumentException();
        }
        flyweights.put(key, person);
        return person;
    }
}

package com.example.labjee.helpers.flyweight;

public class FlyweightActor extends FlyweightPerson {
    public static int num;

    public FlyweightActor() {
        num++;
    }

    @Override
    String getRoleText() {
        return " played in this movie";
    }
}

package com.example.labjee.helpers.flyweight;

//Tydzień 8 - odwrócenie zależności - klasa implementacyjna
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

package com.example.labjee.helpers.flyweight;

//Tydzień 8 - odwrócenie zależności - klasa implementacyjna
public class FlyweightStaff extends FlyweightPerson {

    public static int num;

    public FlyweightStaff() {
        num++;
    }
    @Override
    String getRoleText() {
        return " worked on this movie";
    }
}

package com.example.labjee.helpers.forcedImplementation;

public class Programmer extends Man {
    public Programmer(String name) {
        this.importance = 2;
        this.name = name;
    }

    public String getCoffee() {
        return this.name + " got Black coffee";
    }
}
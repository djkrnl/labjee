package com.example.labjee.helpers.forcedImplementation;

public class ProjectManager extends Man {
    public ProjectManager(String name) {
        this.importance = 1;
        this.name = name;
    }

    public String getCoffee() {
        return this.name + " got Latte";
    }
}
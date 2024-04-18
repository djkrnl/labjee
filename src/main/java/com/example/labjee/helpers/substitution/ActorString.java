package com.example.labjee.helpers.substitution;

import com.example.labjee.models.Person;

// Tydzień 8 - podstawienie Liskov - przykład 2 - klasa pochodna
public class ActorString extends PersonString {
    String role;

    public ActorString(Person person, String role) {
        super(person);
        this.role = role;
    }
}

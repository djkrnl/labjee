package com.example.labjee.helpers.substitution;

import com.example.labjee.models.Person;

// Tydzień 8 - podstawienie Liskov - przykład 2 - klasa bazowa
public class PersonString {
    Person person;

    public String string() {
        return person.getName() + " is a movie";
    }


    public PersonString(Person person) {
        this.person = person;
    }
}

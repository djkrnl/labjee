package com.example.labjee.seeders;

// Tydzień 5 - wzorzec Iterator - interfejs definiujący implementację listy seederów wraz z metodą zwracającą jej iterator
public interface SeederSetInterface {
    void add(Seeder seeder);

    void remove(Seeder seeder);

    SeederSetIterator getIterator();
}
// Tydzień 5 - wzorzec Iterator - koniec

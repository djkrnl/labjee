package com.example.labjee.seeders;

// Tydzień 5 - wzorzec Iterator - interfejs definiujący iterator listy seederów, z metodą next() umożliwiającą przejście iteratora do następnego seedera i metodą last() sprawdzającą, czy znajduje się on na ostatnim elemencie listy
public interface SeederSetIterator {
    Seeder next();

    boolean last();
}
// Tydzień 5 - wzorzec Iterator - koniec
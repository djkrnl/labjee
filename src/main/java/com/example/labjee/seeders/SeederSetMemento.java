package com.example.labjee.seeders;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

// Tydzień 5 - wzorzec Memento - klasa będąca tzw. memento, przechowującym stan listy seederów w momencie utworzenia obiektu
@Getter
@AllArgsConstructor
public class SeederSetMemento {
    private List<Seeder> seederList;
}
// Tydzień 5 - wzorzec Memento - koniec
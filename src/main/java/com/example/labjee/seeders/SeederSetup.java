package com.example.labjee.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Tydzień 3 - wzorzec Composite - zastosowanie 1
@Component
public class SeederSetup implements CommandLineRunner {
    @Autowired
    CountrySeeder countrySeeder;

    @Autowired
    GenreSeeder genreSeeder;

    @Override
    public void run(String... args) throws Exception {
        SeederSet seederSet = new SeederSet();
        seederSet.add(genreSeeder);

        // Tydzień 5 - wzorzec Memento - zastosowanie 1
        SeederSetCaretaker seederSetCaretaker = new SeederSetCaretaker();
        SeederSetMemento seederSetMemento = seederSet.saveToMemento();
        seederSetCaretaker.addMemento(seederSetMemento);

        seederSet.add(countrySeeder);

        seederSetMemento = seederSetCaretaker.getMemento();
        seederSet.undoFromMemento(seederSetMemento);
        // Tydzień 5 - wzorzec Memento - zastosowanie 1 - koniec

        countrySeeder.seed();
        seederSet.seed();
    }
}
// Tydzień 3 - wzorzec Composite - zastosowanie 1 - koniec
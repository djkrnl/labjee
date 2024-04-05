package com.example.labjee.seeders.state;

import com.example.labjee.repositories.GenreRepository;
import com.example.labjee.seeders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Tydzień 6 - wzorzec State - klasa stanu niewypełnionego dla SeederSetup, metoda seed() dla tego stanu wypełnia odpowiednie tabele
@Component
public class UnseededState implements SeederSetupState {
    SeederSetup seederSetup;

    @Autowired
    CountrySeeder countrySeeder;

    @Autowired
    GenreSeeder genreSeeder;

    public UnseededState(SeederSetup seederSetup) {
        this.seederSetup = seederSetup;
    }

    // Tydzień 3 - wzorzec Composite - zastosowanie 1
    @Override
    public void seed() {
        System.out.println("Seeding in progress");

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

        seederSetup.setState(seederSetup.getSeededState());
        System.out.println("Seeding completed");
    }
    // Tydzień 3 - wzorzec Composite - zastosowanie 1 - koniec
}
// Tydzień 6 - wzorzec State - koniec

package com.example.labjee.seeders;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

// Tydzień 3 - wzorzec Composite - klasa będąca kompozytem implementującym utworzone seedery w formie listy
// Tydzień 7 - zasada otwarte-zamknięte
@Component
public class SeederSet implements Seeder, SeederSetInterface {
    private List<Seeder> seederList = new ArrayList<>();

    // Tydzień 5 - wzorzec Iterator - zastosowanie 1
    @Override
    public void seed() {
        SeederSetIterator seederSetIterator = this.getIterator();

        while (!seederSetIterator.last()) {
            Seeder seeder = seederSetIterator.next();
            seeder.seed();
        }
    }
    // Tydzień 5 - wzorzec Iterator - zastosowanie 1 - koniec

    @Override
    public void add(Seeder seeder) {
        this.seederList.add(seeder);
    }


    @Override
    public void remove(Seeder seeder) {
        this.seederList.remove(seeder);
    }

    @Override
    public SeederSetIterator getIterator() {
        return new SeederSetIteratorImpl(this.seederList);
    }

    public SeederSetMemento saveToMemento() {
        return new SeederSetMemento(new ArrayList<>(this.seederList));
    }
    public void undoFromMemento(SeederSetMemento memento) {
        this.seederList = memento.getSeederList();
    }
}
// Tydzień 3 - wzorzec Composite - koniec
package com.example.labjee.seeders;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

// Tydzień 3 - wzorzec Composite - klasa będąca kompozytem implementującym utworzone seedery w formie listy
@Component
public class SeederSet implements Seeder {
    private List<Seeder> seederList = new ArrayList<>();

    @Override
    public void seed() {
        for (Seeder seeder : seederList) {
            seeder.seed();
        }
    }

    public void add(Seeder seeder) {
        this.seederList.add(seeder);
    }

    public void remove(Seeder seeder) {
        this.seederList.remove(seeder);
    }

    public void clear() {
        this.seederList.clear();
    }
}
// Tydzień 3 - wzorzec Composite - koniec
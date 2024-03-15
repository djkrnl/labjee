package com.example.labjee.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

// Tydzień 3 - wzorzec Composite - klasa będąca kompozytem implementującym utworzone seedery w formie listy i uruchamiająca je wraz ze startem aplikacji
@Component
public class SeederSet implements Seeder, CommandLineRunner {
    private List<Seeder> seederList = new ArrayList<>();

    @Autowired
    CountrySeeder countrySeeder;

    @Autowired
    GenreSeeder genreSeeder;

    @Override
    public void run(String... args) throws Exception {
        this.add(countrySeeder);
        this.add(genreSeeder);

        seed();
    }

    public void seed() {
        for (Seeder seeder : seederList) {
            seeder.seed();
        }
    }

    private void add(Seeder seeder) {
        this.seederList.add(seeder);
    }

    /*
    public void add(Seeder seeder) {
        this.seederList.add(seeder);
    }

    public void remove(Seeder seeder) {
        this.seederList.remove(seeder);
    }

    public void clear() {
        this.seederList.clear();
    }
    */
}
// Tydzień 3 - wzorzec Composite - koniec
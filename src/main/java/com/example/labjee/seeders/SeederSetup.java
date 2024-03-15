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

        countrySeeder.seed();
        seederSet.seed();
    }
}
// Tydzień 3 - wzorzec Composite - zastosowanie 1 - koniec
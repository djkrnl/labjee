package com.example.labjee.seeders;

import com.example.labjee.helpers.GenresPrototype;
import com.example.labjee.models.Genre;
import com.example.labjee.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GenreSeeder implements CommandLineRunner {
    @Autowired
    GenreRepository genreRepository;

    @Override
    public void run(String... args) throws Exception {
        seed();
    }

    // Tydzień 2 - wzorzec Prototype - zastosowanie 1
    private void seed() {
        if (genreRepository.count() == 0) {
            GenresPrototype genres = new GenresPrototype();
            genres.loadData();

            genreRepository.saveAll(genres.getGenres());
        }
    }
    // Tydzień 2 - wzorzec Prototype - zastosowanie 1 - koniec
}

package com.example.labjee.seeders;

import com.example.labjee.helpers.GenresPrototype;
import com.example.labjee.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Tydzień 3 - wzorzec Composite - klasa będąca liściem interfejsu Seeder, odpowiedzialnym za wypełnianie tablicy gatunków
// Tydzień 7 - zasada otwarte-zamknięte
@Component
public class GenreSeeder implements Seeder {
    @Autowired
    GenreRepository genreRepository;

    // Tydzień 2 - wzorzec Prototype - zastosowanie 1
    @Override
    public void seed() {
        GenresPrototype genres = new GenresPrototype();
        genres.loadData();

        this.genreRepository.saveAll(genres.getGenres());
    }
    // Tydzień 2 - wzorzec Prototype - zastosowanie 1 - koniec
}
// Tydzień 3 - wzorzec Composite - koniec
package com.example.labjee.seeders;

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

    private void seed() {
        if (genreRepository.count() == 0) {
            Genre genre_1 = new Genre("Akcja");
            Genre genre_2 = new Genre("Animowany");
            Genre genre_3 = new Genre("Biograficzny");
            Genre genre_4 = new Genre("Dokumentalny");
            Genre genre_5 = new Genre("Dramat");
            Genre genre_6 = new Genre("Familijny");
            Genre genre_7 = new Genre("Fantasy");
            Genre genre_8 = new Genre("Film noir");
            Genre genre_9 = new Genre("Gangsterski");
            Genre genre_10 = new Genre("Historyczny");
            Genre genre_11 = new Genre("Horror");
            Genre genre_12 = new Genre("Katastroficzny");
            Genre genre_13 = new Genre("Komediowy");
            Genre genre_14 = new Genre("Kryminalny");
            Genre genre_15 = new Genre("Krótkometrażowy");
            Genre genre_16 = new Genre("Melodramat");
            Genre genre_17 = new Genre("Musical");
            Genre genre_18 = new Genre("Muzyczny");
            Genre genre_19 = new Genre("Przygodowy");
            Genre genre_20 = new Genre("Psychologiczny");
            Genre genre_21 = new Genre("Romantyczny");
            Genre genre_22 = new Genre("Science fiction");
            Genre genre_23 = new Genre("Sensacyjny");
            Genre genre_24 = new Genre("Sportowy");
            Genre genre_25 = new Genre("Thriller");
            Genre genre_26 = new Genre("Western");
            Genre genre_27 = new Genre("Wojenny");
            
            genreRepository.save(genre_1);
            genreRepository.save(genre_2);
            genreRepository.save(genre_3);
            genreRepository.save(genre_4);
            genreRepository.save(genre_5);
            genreRepository.save(genre_6);
            genreRepository.save(genre_7);
            genreRepository.save(genre_8);
            genreRepository.save(genre_9);
            genreRepository.save(genre_10);
            genreRepository.save(genre_11);
            genreRepository.save(genre_12);
            genreRepository.save(genre_13);
            genreRepository.save(genre_14);
            genreRepository.save(genre_15);
            genreRepository.save(genre_16);
            genreRepository.save(genre_17);
            genreRepository.save(genre_18);
            genreRepository.save(genre_19);
            genreRepository.save(genre_20);
            genreRepository.save(genre_21);
            genreRepository.save(genre_22);
            genreRepository.save(genre_23);
            genreRepository.save(genre_24);
            genreRepository.save(genre_25);
            genreRepository.save(genre_26);
            genreRepository.save(genre_27);
        }
    }
}

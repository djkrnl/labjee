package com.example.labjee.helpers;

import com.example.labjee.models.Genre;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// Tydzień 2 - wzorzec Prototype - klasa prototypu tworząca listę gatunków na podstawie domyślnych (testowych) danych bądź danych z zewnątrz, będzie ona wykorzystywana do testów aplikacji w przyszłości, aktualnie używana przez GenreSeeder
@Getter
public class GenresPrototype implements Cloneable {
    private List<Genre> genres;

    public GenresPrototype() {
        this.genres = new ArrayList<Genre>();
    }

    public GenresPrototype(List<Genre> genres) {
        this.genres = genres;
    }

    public void loadData() {
        genres.add(new Genre("Akcja"));
        genres.add(new Genre("Animowany"));
        genres.add(new Genre("Biograficzny"));
        genres.add(new Genre("Dokumentalny"));
        genres.add(new Genre("Dramat"));
        genres.add(new Genre("Familijny"));
        genres.add(new Genre("Fantasy"));
        genres.add(new Genre("Film noir"));
        genres.add(new Genre("Gangsterski"));
        genres.add(new Genre("Historyczny"));
        genres.add(new Genre("Horror"));
        genres.add(new Genre("Katastroficzny"));
        genres.add(new Genre("Komediowy"));
        genres.add(new Genre("Kryminalny"));
        genres.add(new Genre("Krótkometrażowy"));
        genres.add(new Genre("Melodramat"));
        genres.add(new Genre("Musical"));
        genres.add(new Genre("Muzyczny"));
        genres.add(new Genre("Przygodowy"));
        genres.add(new Genre("Psychologiczny"));
        genres.add(new Genre("Romantyczny"));
        genres.add(new Genre("Science fiction"));
        genres.add(new Genre("Sensacyjny"));
        genres.add(new Genre("Sportowy"));
        genres.add(new Genre("Thriller"));
        genres.add(new Genre("Western"));
        genres.add(new Genre("Wojenny"));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();

        List<Genre> clonedGenres = new ArrayList<Genre>(this.getGenres());
        return new GenresPrototype(clonedGenres);
    }
}
// Tydzień 2 - wzorzec Prototype - koniec

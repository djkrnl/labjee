package com.example.labjee.helpers;

import com.example.labjee.models.Country;
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
        genres.addAll(List.of(new Genre("Akcja"), new Genre("Animowany"), new Genre("Biograficzny"), new Genre("Dokumentalny"), new Genre("Dramat"),
                new Genre("Familijny"), new Genre("Fantasy"), new Genre("Film noir"), new Genre("Gangsterski"), new Genre("Historyczny"),
                new Genre("Horror"), new Genre("Katastroficzny"), new Genre("Komediowy"), new Genre("Kryminalny"), new Genre("Krótkometrażowy"),
                new Genre("Melodramat"), new Genre("Musical"), new Genre("Muzyczny"), new Genre("Przygodowy"), new Genre("Psychologiczny"),
                new Genre("Romantyczny"), new Genre("Science fiction"), new Genre("Sensacyjny"), new Genre("Sportowy"), new Genre("Thriller"),
                new Genre("Western"), new Genre("Wojenny")));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();

        List<Genre> clonedGenres = new ArrayList<Genre>(this.getGenres());
        return new GenresPrototype(clonedGenres);
    }
}
// Tydzień 2 - wzorzec Prototype - koniec

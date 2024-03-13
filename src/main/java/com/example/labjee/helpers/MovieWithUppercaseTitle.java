package com.example.labjee.helpers;

import com.example.labjee.models.Movie;

// Tydzień 3 - wzorzec Decorator - klasa rozszerzająca dekorator, a więc wykorzystująca go do modyfikacji tytułu oryginalnego filmu przekazanego jako obiekt w konstruktorze poprzez zamianę wszystkich liter na wielkie
public class MovieWithUppercaseTitle extends MovieDecorator {
    public MovieWithUppercaseTitle(Movie movie) {
        super(movie);

        String originalTitle = this.movie.getOriginalTitle();
        this.movie.setOriginalTitle(originalTitle.toUpperCase());
    }
}
// Tydzień 3 - wzorzec Decorator - koniec
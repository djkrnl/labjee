package com.example.labjee.helpers.substitution;

import com.example.labjee.models.Movie;

// Tydzień 8 - podstawienie Liskov - przykład 1 - klasa pochodna
public class ForeignMovieString extends MovieString {
    String country;

    public ForeignMovieString(Movie movie, String country) {
        super(movie);
        this.country = country;
    }
}

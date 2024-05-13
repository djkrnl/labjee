package com.example.labjee.helpers.substitution;

import com.example.labjee.models.Movie;

// Tydzień 8 - podstawienie Liskov - przykład 1 - klasa bazowa
public class MovieString {
    Movie movie;

    public String makeString() {
        return movie.getTitle() + " is a movie";
    }

    public MovieString(Movie movie) {
        this.movie = movie;
    }
}

package com.example.labjee.helpers.strategy;

import com.example.labjee.models.Movie;
import lombok.Getter;
import lombok.Setter;

// Tydzień 6 - wzorzec Strategy - klasa modyfikatora tytułu filmu wykorzystująca obiekt strategii do zmiany tytułu filmu w obiekcie (metoda modifyAndGet())
public class MovieTitleModifier {
    private MovieTitleStrategy movieTitleStrategy;

    @Getter
    @Setter
    private Movie movie;

    public MovieTitleModifier(MovieTitleStrategy movieTitleStrategy) {
        this.movieTitleStrategy = movieTitleStrategy;
    }

    public Movie modifyAndGet(Movie movie) {
        return movieTitleStrategy.modify(movie);
    }
}
// Tydzień 6 - wzorzec Strategy - koniec
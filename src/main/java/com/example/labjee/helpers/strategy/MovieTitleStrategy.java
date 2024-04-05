package com.example.labjee.helpers.strategy;

import com.example.labjee.models.Movie;

// Tydzień 6 - wzorzec Strategy - interfejs dla obiektów modyfikujących tytuł filmu
public interface MovieTitleStrategy {
    Movie modify(Movie movie);
}
// Tydzień 6 - wzorzec Strategy - koniec

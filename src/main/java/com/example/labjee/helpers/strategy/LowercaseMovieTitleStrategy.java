package com.example.labjee.helpers.strategy;

import com.example.labjee.models.Movie;

// Tydzień 6 - wzorzec Strategy - klasa implementująca interfejs strategii, metoda modify() zmienia tytuł filmu na małe litery oraz zwraca zmodyfikowany obiekt filmu
public class LowercaseMovieTitleStrategy implements MovieTitleStrategy {
    @Override
    public Movie modify(Movie movie) {
        String title = movie.getTitle().toLowerCase();
        movie.setTitle(title);

        return movie;
    }
}
// Tydzień 6 - wzorzec Strategy - koniec
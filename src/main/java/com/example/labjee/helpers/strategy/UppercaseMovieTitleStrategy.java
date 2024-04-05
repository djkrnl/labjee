package com.example.labjee.helpers.strategy;

import com.example.labjee.models.Movie;

// Tydzień 6 - wzorzec Strategy - klasa implementująca interfejs strategii, metoda modify() zmienia tytuł filmu na wielkie litery oraz zwraca zmodyfikowany obiekt filmu
public class UppercaseMovieTitleStrategy implements MovieTitleStrategy {
    @Override
    public Movie modify(Movie movie) {
        String title = movie.getTitle().toUpperCase();
        movie.setTitle(title);

        return movie;
    }
}
// Tydzień 6 - wzorzec Strategy - koniec
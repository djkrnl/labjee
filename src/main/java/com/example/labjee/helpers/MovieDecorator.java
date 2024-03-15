package com.example.labjee.helpers;

import com.example.labjee.models.Movie;
import lombok.Getter;

// Tydzień 3 - wzorzec Decorator - klasa dekoratora stanowiąca podstawę pozwalającą na modyfikację oddzielnych obiektów klasy filmu, zawiera pole przechowujące obiekt
// Brakuje interfejsu implementowanego w obiekcie orignalnym i dekorowanym
@Getter
public class MovieDecorator {
    protected Movie movie;

    public MovieDecorator(Movie movie) {
        this.movie = movie;
    }
}
// Tydzień 3 - wzorzec Decorator - koniec
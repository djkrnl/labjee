package com.example.labjee.helpers;

import com.example.labjee.models.*;
import com.example.labjee.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Tydzień 4 - wzorzec Facade - klasa fasady dla podstawowych obiektów bazy danych o filmach, pozwalająca na zapis bądź aktualizację w bazie podanego jako argument obiektu, metoda createOrUpdate sprawdza typ obiektu i wywołuje metodę z odpowiedniej usługi bądź wyjątek, gdy nie jest to podstawowy obiekt bazy
@Component
public class DatabaseSaverFacade {
    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private PersonService personService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private GenreService genreService;

    public void createOrUpdate(Object object, boolean additionalOption) {
        if (object instanceof User) {
            userService.createOrUpdate((User) object, additionalOption);
        } else if (object instanceof Movie) {
            movieService.createOrUpdate((Movie) object);
        } else if (object instanceof Person) {
            personService.createOrUpdate((Person) object);
        } else if (object instanceof Country) {
            countryService.createOrUpdate((Country) object);
        } else if (object instanceof Genre) {
            genreService.createOrUpdate((Genre) object);
        } else throw new RuntimeException("Nieprawidłowy obiekt");
    }
}
// Tydzień 4 - wzorzec Facade - koniec

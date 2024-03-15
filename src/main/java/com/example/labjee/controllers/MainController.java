package com.example.labjee.controllers;

import com.example.labjee.helpers.UsersLoggedInSingleton;
import com.example.labjee.services.MovieService;
import com.example.labjee.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private PersonService personService;
    
    @GetMapping("/")
    public String indexPage(Model m) {
        m.addAttribute("movies", movieService.getNewest());
        m.addAttribute("persons", personService.getNewest());
        // Tydzień 2 - wzorzec Singleton - pobranie danych
        m.addAttribute("usersLoggedIn", UsersLoggedInSingleton.getInstance().getCount());
        // Tydzień 2 - wzorzec Singleton - pobranie danych - koniec

        return "index";
    }
    
    @GetMapping("/movies")
    public String moviesPage(Model m) {
        m.addAttribute("movies", movieService.getAll());
        
        return "movies";
    }
    
    @GetMapping("/persons")
    public String personsPage(Model m) {
        m.addAttribute("persons", personService.getAll());
        
        return "persons";
    }
}

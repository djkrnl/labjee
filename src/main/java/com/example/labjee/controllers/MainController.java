package com.example.labjee.controllers;

import com.example.labjee.helpers.UsersLoggedInSingleton;
import com.example.labjee.helpers.command.*;
import com.example.labjee.services.MovieService;
import com.example.labjee.services.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.ObjectFactory;
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

    @Autowired
    ObjectFactory<HttpSession> sessionFactory;

    SessionCommandControl sessionCommandControl = new SessionCommandControl();
    
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

    // Tydzień 5 - wzorzec Command - zastosowanie 1
    @GetMapping("/background")
    public String backgroundPage(Model m) {
        HttpSession session = sessionFactory.getObject();
        Background background = new Background(session);

        String backgroundValue = (String) session.getAttribute("background");
        if (backgroundValue != null) {
            if (session.getAttribute("background").equals("dark")) {
                sessionCommandControl.execute(new LightModeCommand(background));
            } else {
                sessionCommandControl.execute(new DarkModeCommand(background));
            }
        } else {
            sessionCommandControl.execute(new DarkModeCommand(background));
        }

        return "redirect:/";
    }

    @GetMapping("/font")
    public String fontPage(Model m) {
        HttpSession session = sessionFactory.getObject();
        Font font = new Font(session);

        String fontValue = (String) session.getAttribute("font");
        if (fontValue != null) {
            if (session.getAttribute("font").equals("large")) {
                sessionCommandControl.execute(new NormalFontCommand(font));
            } else {
                sessionCommandControl.execute(new LargeFontCommand(font));
            }
        } else {
            sessionCommandControl.execute(new LargeFontCommand(font));
        }

        return "redirect:/";
    }
    // Tydzień 5 - wzorzec Command - zastosowanie 1 - koniec
}

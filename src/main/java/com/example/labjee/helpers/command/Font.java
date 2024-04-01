package com.example.labjee.helpers.command;

import jakarta.servlet.http.HttpSession;

// Tydzień 5 - wzorzec Command - klasa zawierająca operacje na sesji (wstrzykiwanej w konstruktorze), ustawiające wartości określające wielkość czcionki w aplikacji (zwykła - normal bądź duża - large)
public class Font {
    HttpSession session;

    public Font(HttpSession session) {
        this.session = session;
    }

    public void large() {
        session.setAttribute("font", "large");
    }

    public void normal() {
        session.setAttribute("font", "normal");
    }
}
// Tydzień 5 - wzorzec Command - koniec
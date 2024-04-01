package com.example.labjee.helpers.command;

import jakarta.servlet.http.HttpSession;

// Tydzień 5 - wzorzec Command - klasa zawierająca operacje na sesji (wstrzykiwanej w konstruktorze), ustawiające wartości określające tryb wyglądu aplikacji (jasny - light bądź ciemny - dark)
public class Background {
    HttpSession session;

    public Background(HttpSession session) {
        this.session = session;
    }

    public void dark() {
        session.setAttribute("background", "dark");
    }

    public void light() {
        session.setAttribute("background", "light");
    }
}

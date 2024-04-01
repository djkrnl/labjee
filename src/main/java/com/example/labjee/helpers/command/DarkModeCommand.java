package com.example.labjee.helpers.command;

// Tydzień 5 - wzorzec Command - polecenie wywołujące zmianę trybu wyglądu na ciemny z użyciem obiektu Background (wstrzykiwanego w konstruktorze)
public class DarkModeCommand implements CommandBase {
    private Background background;

    public DarkModeCommand(Background background) {
        this.background = background;
    }

    @Override
    public void execute() {
        background.dark();
    }

    @Override
    public void undo() {
        background.light();
    }
}
// Tydzień 5 - wzorzec Command - koniec
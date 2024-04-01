package com.example.labjee.helpers.command;

// Tydzień 5 - wzorzec Command - polecenie wywołujące zmianę trybu wyglądu na jasny z użyciem obiektu Background (wstrzykiwanego w konstruktorze)
public class LightModeCommand implements CommandBase {
    private Background background;

    public LightModeCommand(Background background) {
        this.background = background;
    }

    @Override
    public void execute() {
        background.light();
    }

    @Override
    public void undo() {
        background.dark();
    }
}

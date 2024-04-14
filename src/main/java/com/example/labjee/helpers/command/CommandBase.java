package com.example.labjee.helpers.command;

// Tydzień 5 - wzorzec Command - interfejs bazowy dla poleceń
// Tydzień 7 - zasada otwarte-zamknięte
public interface CommandBase {
    void execute();

    void undo();
}
// Tydzień 5 - wzorzec Command - koniec
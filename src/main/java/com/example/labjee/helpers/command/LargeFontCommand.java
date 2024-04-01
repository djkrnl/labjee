package com.example.labjee.helpers.command;

// Tydzień 5 - wzorzec Command - polecenie wywołujące zmianę rozmiaru czcionki na duży z użyciem obiektu Font (wstrzykiwanego w konstruktorze)
public class LargeFontCommand implements CommandBase {
    private Font font;

    public LargeFontCommand(Font font) {
        this.font = font;
    }

    @Override
    public void execute() {
        font.large();
    }

    @Override
    public void undo() {
        font.normal();
    }
}
// Tydzień 5 - wzorzec Command - koniec